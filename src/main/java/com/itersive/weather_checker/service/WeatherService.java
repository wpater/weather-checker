package com.itersive.weather_checker.service;

import com.itersive.weather_checker.model.Weather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@EnableScheduling
public class WeatherService {

    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

    private final String auto = "auto";

    private LocationDetector detector;
    private WeatherRetriever retriever;

    @Value("${com.itersive.weather_checker.location}")
    private Set<String> locations;
    @Value("${com.itersive.weather_checker.location.detect}")
    private String detection;

    public WeatherService(LocationDetector detector, WeatherRetriever retriever) {
        this.detector = detector;
        this.retriever = retriever;
        autodetect();
    }

    // Data in openweathermap is updated every 10min
    @Scheduled(cron = "0 0/10 * * * ?")
    public List<Weather> getWeather() {
        logger.debug("Checking weather for: {}", locations);

        List<Weather> results = new ArrayList<>();
        locations.forEach(location -> {
            Optional<Weather> o = retriever.retrieve(location);
            o.ifPresent(results::add);
        });

        return results;
    }

    public List<List<Weather>> getPrevWeather() {
        logger.debug("Getting old weather for: {}", locations);

        List<List<Weather>> results = new ArrayList<>();
        locations.forEach(location -> results.add(retriever.retrieveStoredWeather(location)));

        return results;
    }

    public void addLocation(String location) {
        this.locations.add(location);
    }

    public void deleteLocation(String location) {
        this.locations.remove(location);
    }

    public Set<String> getLocations() {
        return locations;
    }

    public void setDetection(String detection) {
        this.detection = detection;
    }

    public String getDetection() {
        return detection;
    }

    private void autodetect() {
        if (auto.equals(detection)) {
            Optional<Weather> o = retriever.retrieve(detector.detect());
            o.ifPresent(item -> locations.add(item.getLocation()));
        }
    }
}
