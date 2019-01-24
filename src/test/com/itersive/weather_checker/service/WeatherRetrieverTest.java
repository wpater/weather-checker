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

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class WeatherRetrieverTest {

    @Autowired
    private WeatherRetriever retriever;

    @Test
    public void retrieveWeatherByString() {
        String location = "krakow";

        Optional<Weather> optionalWeather = retriever.retrieve(location);

        assertTrue(optionalWeather.isPresent());

        Weather weather = optionalWeather.get();

        assertNotNull(weather.getLocation());
        assertEquals(location.toLowerCase(), weather.getLocation().toLowerCase());
        assertNotNull(weather.getTemp());
    }

    @Test
    public void retrieveWeatherByWrongString() {
        String location = "krakowabcd";

        Optional<Weather> optionalWeather = retriever.retrieve(location);

        assertFalse(optionalWeather.isPresent());
    }

    @Test
    public void retrieveWeatherByLocation() {
        Coordinates coords = new Coordinates(50.0756, 19.8967);
        Location location = new Location(coords, 0.0);

        Optional<Weather> optionalWeather = retriever.retrieve(location);

        assertTrue(optionalWeather.isPresent());

        Weather weather = optionalWeather.get();

        assertNotNull(weather.getLocation());
        assertEquals("krakow", weather.getLocation().toLowerCase());
        assertNotNull(weather.getTemp());
    }

    @Test
    public void retrieveWeatherByWrongLocation() {
        Coordinates coords = new Coordinates(500756.0, 198967.0);
        Location location = new Location(coords, 0.0);

        Optional<Weather> optionalWeather = retriever.retrieve(location);

        assertFalse(optionalWeather.isPresent());
    }

    @Test
    public void retrieveWeatherFromDB() {
        String location = "Krakow";

        Optional<Weather> optionalWeather = retriever.retrieve(location);

        assertTrue(optionalWeather.isPresent());

        Weather weather = optionalWeather.get();

        List<Weather> list = retriever.retrieveStoredWeather(location);

        assertEquals(1, list.size());
        assertEquals(list.get(0).getLocation().toLowerCase(), weather.getLocation().toLowerCase());
    }
}
