package com.itersive.weather_checker.controller;

import com.itersive.weather_checker.model.Weather;
import com.itersive.weather_checker.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WeatherController {

    private WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("weather")
    public List<Weather> getWeather() {
        return weatherService.getWeather();
    }

    @GetMapping("weather/prev")
    public List<List<Weather>> getWeatherForLocation() {
        return weatherService.getPrevWeather();
    }
}
