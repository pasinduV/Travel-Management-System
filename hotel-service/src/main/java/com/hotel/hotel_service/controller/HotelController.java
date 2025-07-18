package com.hotel.hotel_service.controller;

import com.hotel.hotel_service.dto.*;
import com.hotel.hotel_service.service.HotelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
@CrossOrigin(origins = "*")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    // API 1: Create Hotel
    @PostMapping
    public ResponseEntity<ApiResponse<HotelResponse>> createHotel(@Valid @RequestBody HotelCreateRequest request) {
        try {
            HotelResponse response = hotelService.createHotel(request);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.success("Hotel created successfully", response));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    // API 2: Get All Hotels
    @GetMapping
    public ResponseEntity<ApiResponse<List<HotelResponse>>> getAllHotels() {
        try {
            List<HotelResponse> hotels = hotelService.getAllHotels();
            return ResponseEntity.ok(ApiResponse.success("Hotels retrieved successfully", hotels));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    // API 3: Get Hotel by ID with Details
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<HotelResponse>> getHotelById(@PathVariable Long id) {
        try {
            HotelResponse response = hotelService.getHotelById(id);
            return ResponseEntity.ok(ApiResponse.success("Hotel retrieved successfully", response));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    // API 4: Update Hotel
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<HotelResponse>> updateHotel(@PathVariable Long id,
            @Valid @RequestBody HotelUpdateRequest request) {
        try {
            HotelResponse response = hotelService.updateHotel(id, request);
            return ResponseEntity.ok(ApiResponse.success("Hotel updated successfully", response));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    // Bonus API: Delete Hotel
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteHotel(@PathVariable Long id) {
        try {
            hotelService.deleteHotel(id);
            return ResponseEntity.ok(ApiResponse.success("Hotel deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    // Bonus API: Get Hotels by Location
    @GetMapping("/location/{locationId}")
    public ResponseEntity<ApiResponse<List<HotelResponse>>> getHotelsByLocation(@PathVariable Long locationId) {
        try {
            List<HotelResponse> hotels = hotelService.getHotelsByLocation(locationId);
            return ResponseEntity.ok(ApiResponse.success("Hotels retrieved successfully", hotels));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }
}