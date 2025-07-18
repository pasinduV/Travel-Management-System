package com.transport.transport_service.dto;

public class LocationResponse {
    private Long id;
    private String country;
    private String region;

    // Constructors
    public LocationResponse() {
    }

    public LocationResponse(Long id, String country, String region) {
        this.id = id;
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
}
