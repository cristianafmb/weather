package com.example.demo.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "city")
@NoArgsConstructor
public class City {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;
    
    @Column
    private String region;

    @OneToMany(cascade=CascadeType.ALL)
    private List<Weather> weathers = new ArrayList<>();

    public City(String name, String region ) {
        this.name = name;
        this.region = region;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public List<Weather> getWeathers() {
        return weathers;
    }
    public void addWeather(Weather weather) {
        this.weathers.add(weather);
        weather.setCity(this);
    }
}
