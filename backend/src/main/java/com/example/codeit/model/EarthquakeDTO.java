package com.example.codeit.model;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EarthquakeDTO {
    private Long id;
    private Double magnitude;

    private String magType;

    private String place;
    private String title;

    private Long time;
    private Double latitude;
    private Double longitude;

    public EarthquakeDTO() {
    }

    public EarthquakeDTO(Long id, Double magnitude, String magType, String place, String title, Long time, Double latitude, Double longitude) {
        this.id = id;
        this.magnitude = magnitude;
        this.magType = magType;
        this.place = place;
        this.title = title;
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
