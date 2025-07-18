package com.transport.transport_service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import com.transport.transport_service.entity.TransportType;
import java.time.LocalDate;

public class TransportCreateRequest {
    // Optional: either courseId OR (departureFromId + arrivalAtId + type) should be provided
    private Long courseId;

    @NotNull(message = "Capacity is required")
    @Positive(message = "Capacity must be positive")
    private Integer capacity;

    @NotNull(message = "Departure date is required")
    private LocalDate departureDate;

    @NotNull(message = "Price per adult is required")
    @Positive(message = "Price per adult must be positive")
    private Float pricePerAdult;

    // Optional: for creating course inline
    private Long departureFromId;
    private Long arrivalAtId;
    private TransportType type;

    // Constructors
    public TransportCreateRequest() {
    }

    // Getters and Setters
    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public Float getPricePerAdult() {
        return pricePerAdult;
    }

    public void setPricePerAdult(Float pricePerAdult) {
        this.pricePerAdult = pricePerAdult;
    }

    public Long getDepartureFromId() {
        return departureFromId;
    }

    public void setDepartureFromId(Long departureFromId) {
        this.departureFromId = departureFromId;
    }

    public Long getArrivalAtId() {
        return arrivalAtId;
    }

    public void setArrivalAtId(Long arrivalAtId) {
        this.arrivalAtId = arrivalAtId;
    }

    public TransportType getType() {
        return type;
    }

    public void setType(TransportType type) {
        this.type = type;
    }
}
