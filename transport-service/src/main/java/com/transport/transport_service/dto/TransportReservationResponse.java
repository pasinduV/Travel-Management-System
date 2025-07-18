package com.transport.transport_service.dto;

public class TransportReservationResponse {
    private Long idTransportReservation;
    private TransportResponse transport;
    private Integer numberOfSeats;

    // Constructors
    public TransportReservationResponse() {
    }

    public TransportReservationResponse(Long idTransportReservation, TransportResponse transport, Integer numberOfSeats) {
        this.idTransportReservation = idTransportReservation;
        this.transport = transport;
        this.numberOfSeats = numberOfSeats;
    }

    // Getters and Setters
    public Long getIdTransportReservation() {
        return idTransportReservation;
    }

    public void setIdTransportReservation(Long idTransportReservation) {
        this.idTransportReservation = idTransportReservation;
    }

    public TransportResponse getTransport() {
        return transport;
    }

    public void setTransport(TransportResponse transport) {
        this.transport = transport;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
}
