// filepath: hotel-service/src/main/java/com/hotel/hotel_service/dto/RoomResponse.java
package com.hotel.hotel_service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RoomResponse {
    private Long idRoom;
    private String name;
    private Integer guestCapacity;
    private BigDecimal pricePerAdult;
    private String description;
    private Boolean isAvailable;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructors
    public RoomResponse() {
    }

    public RoomResponse(Long idRoom, String name, Integer guestCapacity,
            BigDecimal pricePerAdult, String description, Boolean isAvailable,
            LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.idRoom = idRoom;
        this.name = name;
        this.guestCapacity = guestCapacity;
        this.pricePerAdult = pricePerAdult;
        this.description = description;
        this.isAvailable = isAvailable;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public Long getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(Long idRoom) {
        this.idRoom = idRoom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGuestCapacity() {
        return guestCapacity;
    }

    public void setGuestCapacity(Integer guestCapacity) {
        this.guestCapacity = guestCapacity;
    }

    public BigDecimal getPricePerAdult() {
        return pricePerAdult;
    }

    public void setPricePerAdult(BigDecimal pricePerAdult) {
        this.pricePerAdult = pricePerAdult;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
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