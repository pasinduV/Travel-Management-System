package com.transport.transport_service.controller;

import com.transport.transport_service.dto.*;
import com.transport.transport_service.service.TransportService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transports")
@CrossOrigin(origins = "*")
public class TransportController {

    @Autowired
    private TransportService transportService;

    // API 1: Create Transport
    @PostMapping
    public ResponseEntity<ApiResponse<TransportResponse>> createTransport(@Valid @RequestBody TransportCreateRequest request) {
        try {
            TransportResponse response = transportService.createTransport(request);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.success("Transport created successfully", response));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    // API 2: Get All Transports
    @GetMapping
    public ResponseEntity<ApiResponse<List<TransportResponse>>> getAllTransports() {
        try {
            List<TransportResponse> transports = transportService.getAllTransports();
            return ResponseEntity.ok(ApiResponse.success("Transports retrieved successfully", transports));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    // API 3: Get Transport by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TransportResponse>> getTransportById(@PathVariable Long id) {
        try {
            TransportResponse response = transportService.getTransportById(id);
            return ResponseEntity.ok(ApiResponse.success("Transport retrieved successfully", response));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    // API 4: Delete Transport
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTransport(@PathVariable Long id) {
        try {
            transportService.deleteTransport(id);
            return ResponseEntity.ok(ApiResponse.success("Transport deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    // Additional endpoints for transport courses
    @GetMapping("/courses")
    public ResponseEntity<ApiResponse<List<TransportCourseResponse>>> getAllTransportCourses() {
        try {
            List<TransportCourseResponse> courses = transportService.getAllTransportCourses();
            return ResponseEntity.ok(ApiResponse.success("Transport courses retrieved successfully", courses));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<ApiResponse<List<TransportResponse>>> getTransportsByCourseId(@PathVariable Long courseId) {
        try {
            List<TransportResponse> transports = transportService.getTransportsByCourseId(courseId);
            return ResponseEntity.ok(ApiResponse.success("Transports for course retrieved successfully", transports));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    // Reservation endpoints
    @PostMapping("/reservations")
    public ResponseEntity<ApiResponse<TransportReservationResponse>> createReservation(@Valid @RequestBody TransportReservationCreateRequest request) {
        try {
            TransportReservationResponse response = transportService.createReservation(request);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.success("Transport reservation created successfully", response));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/reservations")
    public ResponseEntity<ApiResponse<List<TransportReservationResponse>>> getAllReservations() {
        try {
            List<TransportReservationResponse> reservations = transportService.getAllReservations();
            return ResponseEntity.ok(ApiResponse.success("Transport reservations retrieved successfully", reservations));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }
}
