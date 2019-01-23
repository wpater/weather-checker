package com.itersive.weather_checker.service;

import com.itersive.weather_checker.App;
import com.itersive.weather_checker.model.Coordinates;
import com.itersive.weather_checker.model.Location;
import com.itersive.weather_checker.model.Weather;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class WeatherRetrieverTest {

    @Autowired
    private WeatherRetriever retriever;

    @Test
    public void retrieveWeatherByString() {
        String location = "krakow";

        Weather weather = retriever.retrieve(location);

        assertNotNull(weather.getLocation());
        assertEquals(location.toLowerCase(), weather.getLocation().toLowerCase());
        assertNotNull(weather.getTemp());
    }

    @Test
    public void retrieveWeatherByLocation() {
        Coordinates coords = new Coordinates(50.0756, 19.8967);
        Location location = new Location(coords, 0.0);

        Weather weather = retriever.retrieve(location);

        assertNotNull(weather.getLocation());
        assertEquals("krakow", weather.getLocation().toLowerCase());
        assertNotNull(weather.getTemp());
    }
}
