package com.example.codeit.service;

import com.example.codeit.model.Earthquake;
import com.example.codeit.model.EarthquakeDTO;
import com.example.codeit.model.EarthquakeMapDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;


public interface EarthquakeService {

    Optional<Earthquake> findById(Long id);

    List<EarthquakeDTO> findAll();

    List<EarthquakeDTO> getEarthquakes(Long afterTime) throws JsonProcessingException;

    void refreshEarthquakes(Long afterTime) throws JsonProcessingException;

    void deleteById(Long id);

    List<EarthquakeDTO> getMapData();


}
