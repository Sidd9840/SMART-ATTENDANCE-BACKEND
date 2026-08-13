package com.smartattendance.entity;

import jakarta.persistence.*;

@Entity
public class CampusLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String campusName;

    private Double latitude;

    private Double longitude;

    private Double allowedDistance;

    // Default Constructor
    public CampusLocation() {
    }

    // Parameterized Constructor
    public CampusLocation(Integer id, String campusName,
                          Double latitude, Double longitude,
                          Double allowedDistance) {
        this.id = id;
        this.campusName = campusName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.allowedDistance = allowedDistance;
    }

    // Getters & Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCampusName() {
        return campusName;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getAllowedDistance() {
        return allowedDistance;
    }

    public void setAllowedDistance(Double allowedDistance) {
        this.allowedDistance = allowedDistance;
    }
}