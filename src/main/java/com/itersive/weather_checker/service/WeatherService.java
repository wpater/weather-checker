package com.itersive.weather_checker.service;

import com.itersive.weather_checker.model.Weather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
    }

    // Data in openweathermap is updated every 10min
    @Scheduled(cron = "0 0/10 * * * ?")
    public List<Weather> getWeather() {
        logger.debug("Checking weather for: {}", locations);

        return locations.stream()
                .map(location -> retriever.retrieve(location))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public List<List<Weather>> getPrevWeather() {
        logger.debug("Getting old weather for: {}", locations);

        return locations.stream()
                .map(location -> retriever.retrieveStoredWeather(location))
                .collect(Collectors.toList());
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
        autodetect();
    }

    public String getDetection() {
        return detection;
    }

    @PostConstruct
    private void autodetect() {
        logger.debug("Autodetection: {}", detection);
        if (auto.equals(detection)) {
            Optional<Weather> o = retriever.retrieve(detector.detect());
            o.ifPresent(item -> locations.add(item.getLocation()));
            logger.debug("Autodetection done, locations: {}", locations);
        }
    }
}
