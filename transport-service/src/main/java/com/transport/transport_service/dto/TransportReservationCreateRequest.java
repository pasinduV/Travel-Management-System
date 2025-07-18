package com.transport.transport_service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class TransportReservationCreateRequest {
    @NotNull(message = "Transport ID is required")
    private Long transportId;

    @NotNull(message = "Number of seats is required")
    @Positive(message = "Number of seats must be positive")
    private Integer numberOfSeats;

    // Constructors
    public TransportReservationCreateRequest() {
    }

    public TransportReservationCreateRequest(Long transportId, Integer numberOfSeats) {
        this.transportId = transportId;
        this.numberOfSeats = numberOfSeats;
    }

    // Getters and Setters
    public Long getTransportId() {
        return transportId;
    }

    public void setTransportId(Long transportId) {
        this.transportId = transportId;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
}
