package com.transport.transport_service.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String country;

    @Column(nullable = false, length = 100)
    private String region;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "departureFrom", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TransportCourse> departureFromCourses;

    @OneToMany(mappedBy = "arrivalAt", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TransportCourse> arrivalAtCourses;

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
    public Location() {
    }

    public Location(String country, String region) {
        this.country = country;
        this.region = region;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<TransportCourse> getDepartureFromCourses() {
        return departureFromCourses;
    }

    public void setDepartureFromCourses(List<TransportCourse> departureFromCourses) {
        this.departureFromCourses = departureFromCourses;
    }

    public List<TransportCourse> getArrivalAtCourses() {
        return arrivalAtCourses;
    }

    public void setArrivalAtCourses(List<TransportCourse> arrivalAtCourses) {
        this.arrivalAtCourses = arrivalAtCourses;
    }
}
