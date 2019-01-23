package com.itersive.weather_checker.service;

import com.itersive.weather_checker.model.Location;
import com.itersive.weather_checker.model.Weather;
import com.itersive.weather_checker.repository.WeatherRepository;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Service
public class WeatherRetriever {

    private static final Logger logger = LoggerFactory.getLogger(WeatherRetriever.class);

    private final String query = "q";
    private final String appId = "appId";
    private final String units = "units";
    private final String celsius = "metric";
    private final String lon = "lon";
    private final String lat = "lat";

    @Value("${com.itersive.weather_checker.openweathermap.api.url}")
    private String url;
    @Value("${com.itersive.weather_checker.openweathermap.api.id}")
    private String apiId;

    private WeatherRepository repository;
    private RestTemplate restTemplate;

    public WeatherRetriever(WeatherRepository repository) {
        this.repository = repository;
        restTemplate = new RestTemplate();
    }

    public Optional<Weather> retrieve(String location) {
        logger.debug("Retrieving weather from API for location: {}", location);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam(query, location)
                .queryParam(appId, apiId)
                .queryParam(units, celsius);

        Optional<Weather> weather = processRequest(builder);

        weather.ifPresent(this::saveWeather);

        return weather;
    }

    public Optional<Weather> retrieve(Location location) {
        logger.debug("Retrieving weather from API for location: {}", location);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam(lat, location.getCoordinates().getLat())
                .queryParam(lon, location.getCoordinates().getLng())
                .queryParam(appId, apiId)
                .queryParam(units, celsius);

        Optional<Weather> weather = processRequest(builder);

        weather.ifPresent(this::saveWeather);

        return weather;
    }

    private Optional<Weather> processRequest(UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<Weather> response;

        try {
            response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, Weather.class);
            return Optional.ofNullable(response.getBody());
        } catch (HttpClientErrorException e) {
            logger.error("Error during retrieving weather by API call: {}", e.getMessage());
            return Optional.empty();
        }
    }

    private void saveWeather(Weather weather) {
        repository.save(weather);
        logger.debug("{} saved in database.", weather);
    }
}
