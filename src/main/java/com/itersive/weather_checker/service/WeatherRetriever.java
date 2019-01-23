package com.itersive.weather_checker.service;

import com.itersive.weather_checker.model.Weather;
import com.itersive.weather_checker.repository.WeatherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class WeatherRetriever {

    private static final Logger logger = LoggerFactory.getLogger(WeatherRetriever.class);

    private final String query = "q";
    private final String appId = "appId";
    private final String units = "units";
    private final String celcius = "metric";

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

    public Weather retrieve(String location) {
        logger.debug("Retrieving weather from API for location: {}", location);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam(query, location)
                .queryParam(appId, apiId)
                .queryParam(units, celcius);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<Weather> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,
                entity, Weather.class);

        Weather weather = response.getBody();

        saveWeather(weather);

        return weather;
    }

    private void saveWeather(Weather weather) {
        repository.save(weather);
        logger.debug("{} saved in database.", weather);
    }
}
