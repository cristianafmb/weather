package com.example.demo.Model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Entity(name = "weather")
@NoArgsConstructor
public class Weather {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private Double min;
    
    @Column
    private Double max;

    @Column
    private Integer precipitation;

    @Column
    private Integer humidity;

    @Column
    private Integer wind;

    @Column
    private LocalDateTime day;

    private boolean degree = true; // true -> celsius, false -> fahrenheit
    private String stringDay;
    private Double atualDegree = 0.0;


    @ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private City city;

    public Weather(LocalDateTime day, Double atual,  Double min, Double max, Integer precipitation, Integer humidity, Integer wind, City city) {
        this.day = day;
        this.atualDegree = atual;
        this.min = min;
        this.max = max;
        this.precipitation = precipitation;
        this.humidity = humidity;
        this.wind = wind;
        this.city = city;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String format = day.format(formatter);
        this.stringDay = format;
    }
    public boolean getDegree() {
        return degree;
    }

    public void setDegree(Boolean degree) {
        this.degree = degree;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(double d) {
        this.min = d;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(double d) {
        this.max = d;
    }


    public Integer getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(Integer precipitation) {
        this.precipitation = precipitation;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Integer getWind() {
        return wind;
    }

    public void setWind(Integer wind) {
        this.wind = wind;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public LocalDateTime getDay(){
        return day;
    }

    public void setDay(LocalDateTime day){
        this.day = day;
    }

    public String getStringDay (){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String format = day.format(formatter);
        return format;
    }


    public Double getAtualDegree(){
        return this.atualDegree;
    }

    public void setAtualDegree(Double degree){
        this.atualDegree = degree;
    }

}
