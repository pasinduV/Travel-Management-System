package com.transport.transport_service.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "transport")
public class Transport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transport")
    private Long idTransport;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private TransportCourse course;

    @Column(nullable = false)
    private Integer capacity;

    @Column(name = "departuredate", nullable = false)
    private LocalDate departureDate;

    @Column(name = "priceperadult", nullable = false)
    private Float pricePerAdult;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "transport", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TransportReservation> reservations;

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
    public Transport() {
    }

    public Transport(TransportCourse course, Integer capacity, LocalDate departureDate, Float pricePerAdult) {
        this.course = course;
        this.capacity = capacity;
        this.departureDate = departureDate;
        this.pricePerAdult = pricePerAdult;
    }

    // Getters and Setters
    public Long getIdTransport() {
        return idTransport;
    }

    public void setIdTransport(Long idTransport) {
        this.idTransport = idTransport;
    }

    public TransportCourse getCourse() {
        return course;
    }

    public void setCourse(TransportCourse course) {
        this.course = course;
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

    public List<TransportReservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<TransportReservation> reservations) {
        this.reservations = reservations;
    }
}
