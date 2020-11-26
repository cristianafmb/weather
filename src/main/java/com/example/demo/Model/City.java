package com.example.demo.Model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "city")
@NoArgsConstructor
public class City {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;
    
    @Column
    private String region;

    @OneToMany(cascade=CascadeType.ALL)
    private Set<Weather> weathers = new HashSet<>();

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

    public Set<Weather> getWeathers() {
        return weathers;
    }
    public void addWeather(Weather weather) {
        this.weathers.add(weather);
        weather.setCity(this);
    }
}
