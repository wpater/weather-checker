package com.itersive.weather_checker.service;

import com.itersive.weather_checker.App;
import com.itersive.weather_checker.model.Location;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class LocationDetectorTest {

    @Autowired
    private LocationDetector detector;

    // There is not a lot logic - purpose of this tests is only to be sure that MSL API works and coords are filled.
    @Test
    public void detect() {
        Location location = detector.detect();

        assertNotNull(location);
        assertNotNull(location.getCoordinates());
        assertNotNull(location.getCoordinates().getLat());
        assertNotNull(location.getCoordinates().getLng());
    }
}
