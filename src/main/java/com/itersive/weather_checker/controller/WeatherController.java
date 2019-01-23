package com.itersive.weather_checker.controller;

import com.itersive.weather_checker.model.Weather;
import com.itersive.weather_checker.repository.WeatherRepository;
import com.itersive.weather_checker.service.LocationDetector;
import com.itersive.weather_checker.service.WeatherRetriever;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("weather")
public class WeatherController {

    private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);

    private final String auto = "auto";

    private LocationDetector detector;
    private WeatherRetriever retriever;

    @Value("${com.itersive.weather_checker.location}")
    private Set<String> locations;
    @Value("${com.itersive.weather_checker.location.detect}")
    private String detection;

    public WeatherController(LocationDetector detector, WeatherRetriever retriever) {
        this.detector = detector;
        this.retriever = retriever;
        autodetect();
    }

    @GetMapping
    public List<Weather> getWeather() {
        logger.debug("Checking weather for: {}", locations);

        List<Weather> results = new ArrayList<>();
        locations.forEach(location -> {
            Optional<Weather> o = retriever.retrieve(location);
            o.ifPresent(results::add);
        });

        return results;
    }

    @GetMapping("prev")
    public List<Weather> getWeatherForLocation(@RequestParam String location) {
        logger.debug("Getting old weather for: {}", location);
        return retriever.retrieveStoredWeather(location);
    }

    private void autodetect() {
        if (auto.equals(detection)) {
            Optional<Weather> o = retriever.retrieve(detector.detect());
            o.ifPresent(item -> locations.add(item.getLocation()));
        }
    }
}
