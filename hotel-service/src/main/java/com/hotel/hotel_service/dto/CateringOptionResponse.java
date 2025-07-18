// filepath: hotel-service/src/main/java/com/hotel/hotel_service/dto/CateringOptionResponse.java
package com.hotel.hotel_service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CateringOptionResponse {
    private Long idCateringOption;
    private String type;
    private BigDecimal price;
    private BigDecimal rating;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructors
    public CateringOptionResponse() {
    }

    public CateringOptionResponse(Long idCateringOption, String type, BigDecimal price,
            BigDecimal rating, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.idCateringOption = idCateringOption;
        this.type = type;
        this.price = price;
        this.rating = rating;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public Long getIdCateringOption() {
        return idCateringOption;
    }

    public void setIdCateringOption(Long idCateringOption) {
        this.idCateringOption = idCateringOption;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
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