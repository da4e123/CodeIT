package com.example.codeit.web;

import com.example.codeit.model.EarthquakeDTO;
import com.example.codeit.model.EarthquakeMapDTO;
import com.example.codeit.service.EarthquakeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/earthquakes")
public class EarthquakeController {

    private final EarthquakeService earthquakeService;

    public EarthquakeController(EarthquakeService earthquakeService) {
        this.earthquakeService = earthquakeService;
    }

    @GetMapping
    public ResponseEntity<List<EarthquakeDTO>> findAll(){
        return ResponseEntity.ok(earthquakeService.findAll());

    }
    @GetMapping("/filtered")
    public ResponseEntity<List<EarthquakeDTO>> getEarthquakes(
            @RequestParam(required = false) Long afterTime
    ) throws JsonProcessingException {
        List<EarthquakeDTO> result = earthquakeService.getEarthquakes(afterTime);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/refresh")
    public ResponseEntity<String> refreshEarthquakes(
            @RequestParam Long afterTime
    ) throws JsonProcessingException {
        earthquakeService.refreshEarthquakes(afterTime);
        return ResponseEntity.ok("Data refreshed successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEarthquake(@PathVariable Long id){
        earthquakeService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/map")
    public ResponseEntity<List<EarthquakeDTO>> getMapData(){
        return ResponseEntity.ok(earthquakeService.getMapData());
    }
}
