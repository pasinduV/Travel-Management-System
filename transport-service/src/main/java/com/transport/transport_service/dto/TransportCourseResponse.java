package com.transport.transport_service.dto;

import com.transport.transport_service.entity.TransportType;

public class TransportCourseResponse {
    private Long id;
    private LocationResponse departureFrom;
    private LocationResponse arrivalAt;
    private TransportType type;

    // Constructors
    public TransportCourseResponse() {
    }

    public TransportCourseResponse(Long id, LocationResponse departureFrom, LocationResponse arrivalAt, TransportType type) {
        this.id = id;
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

    public LocationResponse getDepartureFrom() {
        return departureFrom;
    }

    public void setDepartureFrom(LocationResponse departureFrom) {
        this.departureFrom = departureFrom;
    }

    public LocationResponse getArrivalAt() {
        return arrivalAt;
    }

    public void setArrivalAt(LocationResponse arrivalAt) {
        this.arrivalAt = arrivalAt;
    }

    public TransportType getType() {
        return type;
    }

    public void setType(TransportType type) {
        this.type = type;
    }
}
