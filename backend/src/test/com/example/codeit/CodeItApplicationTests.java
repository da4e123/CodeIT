package com.example.codeit;

import com.example.codeit.model.Earthquake;
import com.example.codeit.model.EarthquakeDTO;
import com.example.codeit.repository.EarthquakeRepository;
import com.example.codeit.service.EarthquakeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

//@ActiveProfiles("test")
@SpringBootTest
class CodeItApplicationTests {

    @Autowired
    private EarthquakeService earthquakeService;

    @Autowired
    private EarthquakeRepository earthquakeRepository;
    @Test
    void testFindAll() {
        List<EarthquakeDTO> result = earthquakeService.findAll();

        assertNotNull(result);
        assertFalse(result.isEmpty(), "Earthquake list should not be empty");
    }


    @Test
    void testMapData() {
        List<EarthquakeDTO> result = earthquakeService.getMapData();

        assertNotNull(result);
        assertFalse(result.isEmpty());

        for (EarthquakeDTO eq : result) {
            assertNotNull(eq.getLatitude());
            assertNotNull(eq.getLongitude());
        }
    }


    @Test
    void testDeleteEarthquake() {
        // Ako ima Id
        Earthquake eq = new Earthquake();
        eq.setMagnitude(5.0);
        eq.setPlace("Test place");
        eq.setLatitude(41.9);
        eq.setLongitude(21.4);

        Earthquake saved = earthquakeRepository.save(eq);
        Long id = saved.getId();


        earthquakeService.deleteById(id);

        Optional<Earthquake> result = earthquakeRepository.findById(id);

        assertTrue(result.isEmpty());
    }

}
