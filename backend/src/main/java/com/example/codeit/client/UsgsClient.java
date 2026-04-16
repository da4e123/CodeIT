package com.example.codeit.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UsgsClient {

    private static final String URL =
            "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_hour.geojson";

    private final RestTemplate restTemplate;

    public UsgsClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String fetchEarthquakes() {
        return restTemplate.getForObject(URL, String.class);
    }
}