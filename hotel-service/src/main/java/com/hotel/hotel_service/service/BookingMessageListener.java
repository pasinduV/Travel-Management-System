package com.hotel.hotel_service.service;

import com.hotel.hotel_service.config.RabbitMQConfig;
import com.hotel.hotel_service.dto.BookingMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class BookingMessageListener {

    @RabbitListener(queues = RabbitMQConfig.BOOKING_CONFIRMATION_QUEUE)
    public void handleBookingConfirmation(BookingMessage bookingMessage) {
        System.out.println("Received booking confirmation: " + bookingMessage);

        try {
            if ("PAYMENT_COMPLETED".equals(bookingMessage.getStatus())) {
                // Handle successful payment
                System.out.println("Payment completed for reservation: " + bookingMessage.getReservationId());
                updateReservationStatus(bookingMessage.getReservationId(), "CONFIRMED");

                // You could also send email confirmations, update inventory, etc.

            } else if ("PAYMENT_FAILED".equals(bookingMessage.getStatus())) {
                // Handle failed payment
                System.out.println("Payment failed for reservation: " + bookingMessage.getReservationId());
                updateReservationStatus(bookingMessage.getReservationId(), "CANCELLED");

                // You could also release inventory, send failure notifications, etc.
            }

        } catch (Exception e) {
            System.err.println("Error handling booking confirmation: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void updateReservationStatus(Long reservationId, String status) {
        // In a real implementation, you would update the database
        // For this demo, we'll just log the status update
        System.out.println("Updated reservation " + reservationId + " status to: " + status);

        // Example:
        // RoomReservation reservation =
        // roomReservationRepository.findById(reservationId);
        // reservation.setStatus(status);
        // roomReservationRepository.save(reservation);
    }
}
