package com.example.codeit.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EarthquakeMapDTO {

    private Double latitude;
    private Double longitude;
    private Double magnitude;
    private String place;

    public EarthquakeMapDTO(Double latitude, Double longitude, Double magnitude, String place) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.magnitude = magnitude;
        this.place = place;
    }
}
