package com.transport.transport_service.dto;

import java.time.LocalDate;

public class TransportResponse {
    private Long idTransport;
    private TransportCourseResponse course;
    private Integer capacity;
    private LocalDate departureDate;
    private Float pricePerAdult;

    // Constructors
    public TransportResponse() {
    }

    public TransportResponse(Long idTransport, TransportCourseResponse course, Integer capacity, LocalDate departureDate, Float pricePerAdult) {
        this.idTransport = idTransport;
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

    public TransportCourseResponse getCourse() {
        return course;
    }

    public void setCourse(TransportCourseResponse course) {
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
}
