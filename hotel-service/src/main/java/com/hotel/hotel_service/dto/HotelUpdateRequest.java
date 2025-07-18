package com.hotel.hotel_service.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import java.math.BigDecimal;

public class HotelUpdateRequest {

    private String name;

    @DecimalMin(value = "0.0", message = "Rating must be between 0.0 and 5.0")
    @DecimalMax(value = "5.0", message = "Rating must be between 0.0 and 5.0")
    private BigDecimal rating;

    private String description;

    private Long locationId;

    private String photos;

    // Constructors
    public HotelUpdateRequest() {
    }

    public HotelUpdateRequest(String name, BigDecimal rating, String description,
            Long locationId, String photos) {
        this.name = name;
        this.rating = rating;
        this.description = description;
        this.locationId = locationId;
        this.photos = photos;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }
}