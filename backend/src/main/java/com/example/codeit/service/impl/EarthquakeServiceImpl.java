package com.example.codeit.service.impl;

import com.example.codeit.client.UsgsClient;
import com.example.codeit.model.Earthquake;
import com.example.codeit.model.EarthquakeDTO;
import com.example.codeit.model.EarthquakeMapper;
import com.example.codeit.repository.EarthquakeRepository;
import com.example.codeit.service.EarthquakeService;
import com.example.codeit.model.exceptions.EarthquakeNotFoundException;
import com.example.codeit.model.exceptions.InvalidAfterTimeException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EarthquakeServiceImpl implements EarthquakeService {

    private final UsgsClient usgsClient;
    private final EarthquakeRepository earthquakeRepository;

    public EarthquakeServiceImpl(UsgsClient usgsClient, EarthquakeRepository earthquakeRepository) {
        this.usgsClient = usgsClient;
        this.earthquakeRepository = earthquakeRepository;
    }

    @Override
    public Optional<Earthquake> findById(Long id) {
        return earthquakeRepository.findById(id);
    }

    @Override
    public List<EarthquakeDTO> findAll() {
        return earthquakeRepository.findAll()
                .stream()
                .map(EarthquakeMapper::toDto)
                .toList();
    }

    @Override
    public List<EarthquakeDTO> getEarthquakes(Long afterTime) throws JsonProcessingException {

        if (afterTime == null) {
            throw new InvalidAfterTimeException();
        }

        String json = usgsClient.fetchEarthquakes();
        List<Earthquake> parsed = parse(json);

        List<Earthquake> filtered = parsed.stream()
                .filter(e -> e.getMagnitude() > 2.0)
                .filter(e -> e.getTime() > afterTime)
                .toList();

        List<Earthquake> saved = earthquakeRepository.saveAll(filtered);

        return saved.stream()
                .map(EarthquakeMapper::toDto)
                .toList();
    }

    @Override
    public void refreshEarthquakes(Long afterTime) throws JsonProcessingException {

        if (afterTime == null) {
            throw new InvalidAfterTimeException();
        }

        String json = usgsClient.fetchEarthquakes();
        List<Earthquake> parsed = parse(json);

        List<Earthquake> filtered = parsed.stream()
                .filter(e -> e.getMagnitude() > 2.0)
                .filter(e -> e.getTime() > afterTime)
                .toList();

        earthquakeRepository.deleteAll();
        earthquakeRepository.saveAll(filtered);
    }

    @Override
    public void deleteById(Long id) {

        Earthquake earthquake = earthquakeRepository.findById(id)
                .orElseThrow(() -> new EarthquakeNotFoundException(id));

        earthquakeRepository.delete(earthquake);
    }

    @Override
    public List<EarthquakeDTO> getMapData() {

        return earthquakeRepository.findAll()
                .stream()
                .map(eq -> new EarthquakeDTO(
                        eq.getId(),
                        eq.getMagnitude(),
                        eq.getMagType(),
                        eq.getPlace(),
                        eq.getTitle(),
                        eq.getTime(),
                        eq.getLatitude(),
                        eq.getLongitude()
                ))
                .toList();
    }

    private List<Earthquake> parse(String json) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(json);
        JsonNode features = root.get("features");

        List<Earthquake> list = new ArrayList<>();

        for (JsonNode feature : features) {

            JsonNode p = feature.get("properties");
            JsonNode geometry = feature.get("geometry");
            JsonNode coordinates = geometry.get("coordinates");

            Earthquake e = new Earthquake();

            e.setMagnitude(p.get("mag").asDouble());
            e.setMagType(p.get("magType").asText());
            e.setPlace(p.get("place").asText());
            e.setTitle(p.get("title").asText());
            e.setTime(p.get("time").asLong());

            e.setLongitude(coordinates.get(0).asDouble());
            e.setLatitude(coordinates.get(1).asDouble());

            list.add(e);
        }

        return list;
    }
}