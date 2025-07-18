package com.hotel.hotel_service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class HotelResponse {
    private Long idHotel;
    private String name;
    private BigDecimal rating;
    private String description;
    private String photos;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Location information
    private LocationResponse location;

    // Related data
    private List<RoomResponse> rooms;
    private List<CateringOptionResponse> cateringOptions;

    // Constructors
    public HotelResponse() {
    }

    public HotelResponse(Long idHotel, String name, BigDecimal rating, String description,
            String photos, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.idHotel = idHotel;
        this.name = name;
        this.rating = rating;
        this.description = description;
        this.photos = photos;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public Long getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(Long idHotel) {
        this.idHotel = idHotel;
    }

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

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
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

    public LocationResponse getLocation() {
        return location;
    }

    public void setLocation(LocationResponse location) {
        this.location = location;
    }

    public List<RoomResponse> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomResponse> rooms) {
        this.rooms = rooms;
    }

    public List<CateringOptionResponse> getCateringOptions() {
        return cateringOptions;
    }

    public void setCateringOptions(List<CateringOptionResponse> cateringOptions) {
        this.cateringOptions = cateringOptions;
    }
}