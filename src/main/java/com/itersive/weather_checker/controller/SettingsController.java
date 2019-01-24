package com.itersive.weather_checker.controller;

import com.itersive.weather_checker.service.WeatherService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("settings")
public class SettingsController {

    private WeatherService weatherService;

    public SettingsController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @PostMapping("location/add")
    public void addLocation(@RequestParam String location) {
        weatherService.addLocation(location);
    }

    @PostMapping("location/del")
    public void deleteLocation(@RequestParam String location) {
        weatherService.deleteLocation(location);
    }

    @PostMapping("detect")
    public void setAutodetection(@RequestParam String auto) {
        weatherService.setDetection(auto);
    }
}
