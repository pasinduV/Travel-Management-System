package com.transport.transport_service.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "transportcourse")
public class TransportCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departurefrom_id", nullable = false)
    private Location departureFrom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arrivalat_id", nullable = false)
    private Location arrivalAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransportType type;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transport> transports;

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
    public TransportCourse() {
    }

    public TransportCourse(Location departureFrom, Location arrivalAt, TransportType type) {
        this.departureFrom = departureFrom;
        this.arrivalAt = arrivalAt;
        this.type = type;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Location getDepartureFrom() {
        return departureFrom;
    }

    public void setDepartureFrom(Location departureFrom) {
        this.departureFrom = departureFrom;
    }

    public Location getArrivalAt() {
        return arrivalAt;
    }

    public void setArrivalAt(Location arrivalAt) {
        this.arrivalAt = arrivalAt;
    }

    public TransportType getType() {
        return type;
    }

    public void setType(TransportType type) {
        this.type = type;
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

    public List<Transport> getTransports() {
        return transports;
    }

    public void setTransports(List<Transport> transports) {
        this.transports = transports;
    }
}
