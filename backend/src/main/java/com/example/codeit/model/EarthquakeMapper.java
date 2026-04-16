package com.example.codeit.model;

public class EarthquakeMapper {

    public static EarthquakeDTO toDto(Earthquake e) {
        EarthquakeDTO dto = new EarthquakeDTO();
        dto.setId(e.getId());
        dto.setMagnitude(e.getMagnitude());
        dto.setMagType(e.getMagType());
        dto.setPlace(e.getPlace());
        dto.setTitle(e.getTitle());
        dto.setTime(e.getTime());
        dto.setLatitude(e.getLatitude());
        dto.setLongitude(e.getLongitude());
        return dto;
    }
}
