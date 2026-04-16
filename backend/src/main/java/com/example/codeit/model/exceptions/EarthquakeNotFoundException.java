package com.example.codeit.model.exceptions;

public class EarthquakeNotFoundException extends RuntimeException{

    public EarthquakeNotFoundException(Long id){
        super(String.format("Earthquake with %d is not found", id));
    }
}
