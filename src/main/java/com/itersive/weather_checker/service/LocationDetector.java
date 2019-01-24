package com.itersive.weather_checker.service;

import com.itersive.weather_checker.model.Location;
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
public class LocationDetector {

    private static final Logger logger = LoggerFactory.getLogger(LocationDetector.class);

    private final String key = "key";

    @Value("${com.itersive.weather_checker.mls.api.url}")
    private String url;
    @Value("${com.itersive.weather_checker.mls.api.id}")
    private String apiId;

    private RestTemplate restTemplate;

    public LocationDetector() {
        this.restTemplate = new RestTemplate();
    }

    Location detect() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam(key, apiId);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<Location> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,
                entity, Location.class);

        Location location = response.getBody();

        logger.debug("Location detected: {}", location);

        return location;
    }
}
