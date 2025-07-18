package com.payment.payment_service.service;

import com.payment.payment_service.config.RabbitMQConfig;
import com.payment.payment_service.dto.BookingMessage;
import com.payment.payment_service.entity.Payment;
import com.payment.payment_service.entity.PaymentMethod;
import com.payment.payment_service.entity.PaymentStatus;
import com.payment.payment_service.repository.PaymentRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class PaymentMessageListener {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final RestTemplate restTemplate = new RestTemplate();
    private final Random random = new Random();

    @RabbitListener(queues = RabbitMQConfig.PAYMENT_QUEUE)
    public void handleBookingPayment(BookingMessage bookingMessage) {
        System.out.println("Received booking payment request: " + bookingMessage);

        try {
            // Create payment record
            Payment payment = new Payment();
            payment.setReservationId(bookingMessage.getReservationId().intValue());
            payment.setUserId(bookingMessage.getUserId());

            if ("HOTEL_BOOKING".equals(bookingMessage.getBookingType())) {
                payment.setRoomReservationId(bookingMessage.getReservationId().intValue());
            } else if ("TRANSPORT_BOOKING".equals(bookingMessage.getBookingType())) {
                payment.setTransportReservationId(bookingMessage.getReservationId().intValue());
            }

            payment.setAmount(bookingMessage.getTotalAmount());
            payment.setStatus(PaymentStatus.PENDING);
            payment.setMethod(PaymentMethod.CARD); // Default to card
            payment.setPaid(false);
            payment.setCreatedAt(LocalDateTime.now());

            // Save payment to database
            Payment savedPayment = paymentRepository.save(payment);
            System.out.println("Payment record created with ID: " + savedPayment.getId());

            // Simulate payment processing (in real world, this would integrate with a
            // payment gateway)
            boolean paymentSuccess = simulatePaymentProcessing();

            if (paymentSuccess) {
                // Update payment status
                savedPayment.setStatus(PaymentStatus.SUCCESS);
                savedPayment.setPaid(true);
                paymentRepository.save(savedPayment);

                System.out.println("Payment successful for reservation: " + bookingMessage.getReservationId());

                // Notify hotel service about successful payment
                notifyHotelService(bookingMessage.getReservationId(), "confirm");

                // Send success message back to booking service
                BookingMessage successMessage = new BookingMessage();
                successMessage.setReservationId(bookingMessage.getReservationId());
                successMessage.setStatus("PAYMENT_COMPLETED");
                successMessage.setBookingType(bookingMessage.getBookingType());

                rabbitTemplate.convertAndSend(
                        RabbitMQConfig.BOOKING_EXCHANGE,
                        RabbitMQConfig.CONFIRMATION_ROUTING_KEY,
                        successMessage);

            } else {
                // Update payment status
                savedPayment.setStatus(PaymentStatus.FAILED);
                savedPayment.setPaid(false);
                paymentRepository.save(savedPayment);

                System.out.println("Payment failed for reservation: " + bookingMessage.getReservationId());

                // Notify hotel service about failed payment
                notifyHotelService(bookingMessage.getReservationId(), "cancel");

                // Send failure message back to booking service
                BookingMessage failureMessage = new BookingMessage();
                failureMessage.setReservationId(bookingMessage.getReservationId());
                failureMessage.setStatus("PAYMENT_FAILED");
                failureMessage.setBookingType(bookingMessage.getBookingType());

                rabbitTemplate.convertAndSend(
                        RabbitMQConfig.BOOKING_EXCHANGE,
                        RabbitMQConfig.CONFIRMATION_ROUTING_KEY,
                        failureMessage);
            }

        } catch (Exception e) {
            System.err.println("Error processing payment: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private boolean simulatePaymentProcessing() {
        try {
            // Simulate processing time
            Thread.sleep(2000);

            // 80% success rate for demo purposes
            return random.nextDouble() < 0.8;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    private void notifyHotelService(Long reservationId, String action) {
        try {
            String hotelServiceUrl = "http://hotel-service:8082/api/bookings/" + reservationId + "/" + action;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<>(headers);

            restTemplate.exchange(hotelServiceUrl, HttpMethod.PUT, entity, String.class);

            System.out.println("Notified hotel service to " + action + " reservation: " + reservationId);
        } catch (Exception e) {
            System.err.println("Failed to notify hotel service: " + e.getMessage());
        }
    }
}
