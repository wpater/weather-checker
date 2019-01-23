package com.itersive.weather_checker.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {

    @JsonProperty("location")
    private Coordinates coordinates;
    private Double accuracy;

    public Location(Coordinates coordinates, Double accuracy) {
        this.coordinates = coordinates;
        this.accuracy = accuracy;
    }

    public Location() {
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Double accuracy) {
        this.accuracy = accuracy;
    }

    @Override
    public String toString() {
        return "Location{" +
                "coordinates=" + coordinates +
                ", accuracy=" + accuracy +
                '}';
    }
}
