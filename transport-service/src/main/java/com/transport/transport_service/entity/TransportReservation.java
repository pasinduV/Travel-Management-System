package com.transport.transport_service.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transportreservation")
public class TransportReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transportreservation")
    private Long idTransportReservation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_transport", nullable = false)
    private Transport transport;

    @Column(name = "numberofseats", nullable = false)
    private Integer numberOfSeats;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Constructors
    public TransportReservation() {
    }

    public TransportReservation(Transport transport, Integer numberOfSeats) {
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

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
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
