package com.example.demo.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.example.demo.Controller.CityController;
import com.example.demo.Model.City;
import com.example.demo.Model.Weather;
import com.example.demo.Repository.CityRepository;
import com.example.demo.Repository.WeatherRepository;
import com.fasterxml.jackson.core.sym.Name;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.stream.Collectors.groupingBy;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.text.Collator;

@Service
public class CityService {
    @Autowired
    public CityRepository cityRepo;
    public WeatherRepository weatherRepo;

    Logger logger = LoggerFactory.getLogger(CityController.class);

    public CityService(CityRepository cityRepo){
        this.cityRepo = cityRepo;
    }


    public List<City> findAll() {
        List<City> listcities = new ArrayList<City>();
        this.cityRepo.findAll().forEach(listcities::add);
        return listcities;
    }


    public List<City> findRegion(String region) {
        List<City> allcities = this.findAll();
        List<City> cityRegion = new ArrayList<>();
        for (City c: allcities){
            if(c.getRegion().equalsIgnoreCase(region)){
                cityRegion.add(c);
            }
        }
        return cityRegion;
    }


	public City findByName(String name) {
        List<City> allcities = this.findAll();
        for(City c: allcities){
            if(c.getName().equals(name)){
                return c;
            }
        }
        return null;
	}


	public Weather weatherToday(City found) {
        LocalDateTime today = LocalDateTime.now();
        Weather todayWeather = null;
        List<Weather> weathers = found.getWeathers();

        for(Weather w: weathers){
            if(w.getTime().getDayOfYear() == today.getDayOfYear()) {
                todayWeather = w;
            }
        }

		return todayWeather;
	}


	public void setCelsius() {
        List<City> allCities = this.findAll();
        for(City c: allCities){
           for(Weather w: c.getWeathers()){
               if(w.getDegree()){
                   w.setMax(w.getMax()-32 / 1.8000);
                   w.setMin(w.getMin()-32 / 1.8000);
                    w.setDegree(false);
               }
           }
        }
    }
    
    public void setFahrenheit() {
        List<City> allCities = this.findAll();
        for(City c: allCities){
           for(Weather w: c.getWeathers()){
               if(!w.getDegree()){
                   w.setMax(w.getMax()* 1.8000 + 32);
                   w.setMin(w.getMin()* 1.8000 + 32);
                    w.setDegree(true);
               }
           }
        }
	}


	public Map<String, Weather> todayWeather() {
        List<City> allCities = this.findAll();

        allCities.sort(Comparator.comparing(City::getName));

        Map<String, Weather> cWeather = new HashMap<>();
        for(City c: allCities){
            cWeather.put(c.getName(), c.getWeathers().get(0));
        }

		return cWeather;
    }
    

}
