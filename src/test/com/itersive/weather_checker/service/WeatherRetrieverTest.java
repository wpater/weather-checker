package com.itersive.weather_checker.service;

import com.itersive.weather_checker.App;
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
    public void retrieveWeatherForLocation() {
        String location = "Cracow";

        Weather weather = retriever.retrieve(location);

        assertNotNull(weather.getLocation());
        assertEquals(location, weather.getLocation());
        assertNotNull(weather.getTemp());
    }
}
