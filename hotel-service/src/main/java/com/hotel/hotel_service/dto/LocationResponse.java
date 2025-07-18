package com.hotel.hotel_service.dto;

import java.time.LocalDateTime;

public class LocationResponse {
    private Long locationId;
    private String country;
    private String region;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructors
    public LocationResponse() {
    }

    public LocationResponse(Long locationId, String country, String region,
            LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.locationId = locationId;
        this.country = country;
        this.region = region;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}