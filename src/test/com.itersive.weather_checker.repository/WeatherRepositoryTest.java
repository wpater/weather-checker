package com.itersive.weather_checker.repository;

import com.itersive.weather_checker.App;
import com.itersive.weather_checker.model.Weather;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class WeatherRepositoryTest {

    @Autowired
    private WeatherRepository repository;

    private Weather entity;

    @Before
    public void init() {
        entity = repository.save(new Weather("Location", 0.0));
    }

    @After
    public void cleanup() {
        repository.delete(entity);
    }

    @Test
    public void findByIdWeather() {
        Optional<Weather> foundEntity = repository.findById(entity.getId());

        assertTrue(foundEntity.isPresent());

        // Simple get, we are sure here that foundEntity is not null
        assertEquals(entity.getLocation(), foundEntity.get().getLocation());
        assertEquals(entity.getTemp(), foundEntity.get().getTemp());
        assertEquals(entity.getDate(), foundEntity.get().getDate());
    }

    @Test
    public void findByLocationWeather() {
        List<Weather> foundEntity = repository.findByLocation(entity.getLocation());

        assertEquals(1, foundEntity.size());

        // Simple get, we are sure here that foundEntity is not null
        assertEquals(entity.getLocation(), foundEntity.get(0).getLocation());
        assertEquals(entity.getTemp(), foundEntity.get(0).getTemp());
        assertEquals(entity.getDate(), foundEntity.get(0).getDate());
    }
}
