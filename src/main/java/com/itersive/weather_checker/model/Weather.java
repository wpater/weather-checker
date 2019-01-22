package com.itersive.weather_checker.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Map;

@Entity
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @JsonProperty("name")
    private String location;

    @NotNull
    private Double temp;

    @NotNull
    private Date date;

    public Weather() {
        this.date = new Date();
    }

    public Weather(@NotBlank String location, @NotNull Double temp) {
        this.location = location;
        this.temp = temp;
        this.date = new Date();
    }


    @JsonProperty("main")
    private void unpackTempFromNestedObject(Map<String, String> main) {
        this.temp = Double.parseDouble(main.get("temp"));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "location='" + location + '\'' +
                ", temp=" + temp +
                ", date=" + date +
                '}';
    }
}
