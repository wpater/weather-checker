package com.itersive.weather_checker.repository;

import com.itersive.weather_checker.model.Weather;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WeatherRepository extends CrudRepository<Weather, Long> {

    // Finding by Id is implemented in CrudRepository

    // Get list of all Weather Entities by location
    List<Weather> findByLocation(String location);
}
