package com.itersive.weather_checker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

@Entity
@Getter
@Setter
@ToString(exclude="id")
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @NotBlank
    @JsonProperty("name")
    private String location;

    @NotNull
    private Double temp;

    @NotNull
    private LocalDateTime date;

    public Weather() {
        this.date = LocalDateTime.now();
    }

    public Weather(@NotBlank String location, @NotNull Double temp) {
        this.location = location;
        this.temp = temp;
        this.date = LocalDateTime.now();
    }


    @JsonProperty("main")
    private void unpackTempFromNestedObject(Map<String, String> main) {
        this.temp = Double.parseDouble(main.get("temp"));
    }
}
