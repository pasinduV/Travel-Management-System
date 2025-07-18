package com.hotel.hotel_service.service;

import com.hotel.hotel_service.config.RabbitMQConfig;
import com.hotel.hotel_service.dto.BookingMessage;
import com.hotel.hotel_service.dto.HotelBookingRequest;
import com.hotel.hotel_service.entity.Hotel;
import com.hotel.hotel_service.entity.Room;
import com.hotel.hotel_service.entity.RoomReservation;
import com.hotel.hotel_service.repository.HotelRepository;
import com.hotel.hotel_service.repository.RoomRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class BookingService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Transactional
    public RoomReservation createHotelBooking(HotelBookingRequest request) {
        // Validate hotel and room exist
        Hotel hotel = hotelRepository.findById(request.getHotelId())
                .orElseThrow(() -> new RuntimeException("Hotel not found with ID: " + request.getHotelId()));

        Room room = roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found with ID: " + request.getRoomId()));

        // Validate room belongs to hotel
        if (!room.getHotel().getIdHotel().equals(hotel.getIdHotel())) {
            throw new RuntimeException("Room does not belong to the specified hotel");
        }

        // Validate dates
        if (request.getCheckOutDate().isBefore(request.getCheckInDate()) ||
                request.getCheckOutDate().isEqual(request.getCheckInDate())) {
            throw new RuntimeException("Check-out date must be after check-in date");
        }

        // Check room availability (simplified - in real world, you'd check for
        // overlapping reservations)
        // For this demo, we'll assume the room is available

        // Calculate total amount
        long numberOfNights = ChronoUnit.DAYS.between(request.getCheckInDate(), request.getCheckOutDate());
        BigDecimal totalAmount = room.getPricePerAdult()
                .multiply(BigDecimal.valueOf(numberOfNights * request.getNumberOfGuests()));

        // Create reservation
        RoomReservation reservation = new RoomReservation();
        reservation.setRoom(room);
        reservation.setIdReservation(System.currentTimeMillis()); // Simulate reservation ID
        reservation.setDateFrom(request.getCheckInDate());
        reservation.setDateTo(request.getCheckOutDate());
        reservation.setTotalAmount(totalAmount);
        reservation.setStatus("PENDING");
        reservation.setCreatedAt(LocalDateTime.now());

        // Save reservation (you'd need to implement the repository save method)
        // For this demo, we'll simulate saving
        reservation.setIdRoomReservation(System.currentTimeMillis()); // Simulate generated ID

        // Send message to payment service
        BookingMessage bookingMessage = new BookingMessage(
                reservation.getIdRoomReservation(),
                hotel.getIdHotel(),
                room.getIdRoom(),
                request.getUserId(),
                request.getCheckInDate(),
                request.getCheckOutDate(),
                totalAmount,
                "PENDING_PAYMENT",
                "HOTEL_BOOKING");

        // Publish message to RabbitMQ
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.BOOKING_EXCHANGE,
                RabbitMQConfig.PAYMENT_ROUTING_KEY,
                bookingMessage);

        System.out.println("Booking message sent to payment service: " + bookingMessage);

        return reservation;
    }

    public void confirmBooking(Long reservationId) {
        // Update reservation status to CONFIRMED
        // In a real implementation, you'd update the database
        System.out.println("Booking confirmed for reservation ID: " + reservationId);

        // Send confirmation message
        BookingMessage confirmationMessage = new BookingMessage();
        confirmationMessage.setReservationId(reservationId);
        confirmationMessage.setStatus("CONFIRMED");
        confirmationMessage.setBookingType("HOTEL_BOOKING");

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.BOOKING_EXCHANGE,
                RabbitMQConfig.CONFIRMATION_ROUTING_KEY,
                confirmationMessage);
    }

    public void cancelBooking(Long reservationId) {
        // Update reservation status to CANCELLED
        // In a real implementation, you'd update the database
        System.out.println("Booking cancelled for reservation ID: " + reservationId);

        // Send cancellation message
        BookingMessage cancellationMessage = new BookingMessage();
        cancellationMessage.setReservationId(reservationId);
        cancellationMessage.setStatus("CANCELLED");
        cancellationMessage.setBookingType("HOTEL_BOOKING");

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.BOOKING_EXCHANGE,
                RabbitMQConfig.CONFIRMATION_ROUTING_KEY,
                cancellationMessage);
    }
}
