package com.hotel.hotel_service.controller;

import com.hotel.hotel_service.dto.ApiResponse;
import com.hotel.hotel_service.dto.HotelBookingRequest;
import com.hotel.hotel_service.entity.RoomReservation;
import com.hotel.hotel_service.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // API: Create Hotel Booking (communicates with Payment Service via RabbitMQ)
    @PostMapping("/hotel")
    public ResponseEntity<ApiResponse<RoomReservation>> createHotelBooking(
            @Valid @RequestBody HotelBookingRequest request) {
        try {
            RoomReservation reservation = bookingService.createHotelBooking(request);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.success(
                            "Hotel booking created successfully. Payment request sent to payment service.",
                            reservation));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    // API: Confirm booking (called when payment is successful)
    @PutMapping("/{reservationId}/confirm")
    public ResponseEntity<ApiResponse<String>> confirmBooking(@PathVariable Long reservationId) {
        try {
            bookingService.confirmBooking(reservationId);
            return ResponseEntity.ok(ApiResponse.success("Booking confirmed successfully",
                    "Reservation " + reservationId + " has been confirmed"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    // API: Cancel booking (called when payment fails)
    @PutMapping("/{reservationId}/cancel")
    public ResponseEntity<ApiResponse<String>> cancelBooking(@PathVariable Long reservationId) {
        try {
            bookingService.cancelBooking(reservationId);
            return ResponseEntity.ok(ApiResponse.success("Booking cancelled successfully",
                    "Reservation " + reservationId + " has been cancelled"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }
}
