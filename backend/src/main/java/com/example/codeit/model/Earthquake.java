package com.example.codeit.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Earthquake {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double magnitude;

    private String magType;

    private String place;
    private String title;

    private Long time;

    private Double latitude;
    private Double longitude;

    public Earthquake() {
    }

    public Earthquake(double magnitude, String magType, String place, String title, Long time) {
        this.magnitude = magnitude;
        this.magType = magType;
        this.place = place;
        this.title = title;
        this.time = time;
    }
}
