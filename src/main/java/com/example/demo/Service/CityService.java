package com.example.demo.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.example.demo.Controller.CityController;
import com.example.demo.Model.City;
import com.example.demo.Model.Weather;
import com.example.demo.Repository.CityRepository;
import com.example.demo.Repository.WeatherRepository;

import org.hibernate.annotations.common.util.impl.Log_.logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {
    @Autowired
    public CityRepository cityRepo;
    @Autowired
    public WeatherRepository weatherRepo;

    Logger logger = LoggerFactory.getLogger(CityController.class);

    public CityService(CityRepository cityRepo) {
        this.cityRepo = cityRepo;
    }

    public List<City> findAll() {
        List<City> listcities = new ArrayList<>();
        this.cityRepo.findAll().forEach(listcities::add);
        return listcities;
    }

    public List<City> findRegion(String region) {
        List<City> allcities = this.findAll();
        List<City> cityRegion = new ArrayList<>();
        for (City c : allcities) {
            if (c.getRegion().equalsIgnoreCase(region)) {
                cityRegion.add(c);
            }
        }
        return cityRegion;
    }

    public City findByName(String name) {
        List<City> allcities = this.findAll();
        for (City c : allcities) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    public Weather weatherToday(City found) {
        LocalDateTime today = LocalDateTime.now();
        ArrayList<Weather> todayWeathers = new ArrayList<>();
        List<Weather> weathers = found.getWeathers();
        Weather todayW = null;

        for (Weather w : weathers) {
            // LOOK FOR THE SAME DAY -> EVERY ENTRIES
            if (w.getDay().getDayOfYear() == today.getDayOfYear()) {
                todayWeathers.add(w);
            }
        }
        Integer hour = today.getHour();

        todayW = checkNearestHour(hour, todayWeathers);
        return todayW;
    }

    private Weather checkNearestHour(Integer hour, ArrayList<Weather> todayWeathers) {
        boolean b = false;
        while (!b) {
            for (int j = 0; j < todayWeathers.size(); j++) {
                if (todayWeathers.get(j).getDay().getHour() == hour) {
                    b = true;
                    return todayWeathers.get(j);
                }
            }
            hour++;
        }
        b=false;
        return null;
    }

    public void setCelsius() {
        List<City> allCities = this.findAll();
        for (City c : allCities) {
            for (Weather w : c.getWeathers()) {
                if (w.getDegree()) {
                    w.setMax(w.getMax() - 32 / 1.8000);
                    w.setMin(w.getMin() - 32 / 1.8000);
                    w.setDegree(false);
                }
            }
        }
    }

    public void setFahrenheit() {
        List<City> allCities = this.findAll();
    
        for (City c : allCities) {
            for (Weather w : c.getWeathers()) {
                if (w.getDegree()) {
                    w.setMax(w.getMax() * 1.8000 + 32);
                    w.setMin(w.getMin() * 1.8000 + 32);
                    w.setDegree(false);
                   this.weatherRepo.save(w);
                }
            }
            this.cityRepo.save(c);
        }
    }

    public Map<String, Weather> todayWeather() {
        List<City> allCities = this.findAll();

        allCities.sort(Comparator.comparing(City::getName));

        TreeMap<String, Weather> cWeather = new TreeMap<>();
        for (City c : allCities) {
            cWeather.put(c.getName(), c.getWeathers().get(0));
        }

        return cWeather;
    }

	public List<Weather> variationWeather(City found) {
        LocalDateTime today = LocalDateTime.now();
        ArrayList<Weather> todayWeathers = new ArrayList<>();
        List<Weather> weathers = found.getWeathers();

        for (Weather w : weathers) {
            // LOOK FOR THE SAME DAY -> EVERY ENTRIES
            if (w.getDay().getDayOfYear() == today.getDayOfYear()) {
                todayWeathers.add(w);
            }
        }
        return todayWeathers;
	}

	public List<Weather> nextDays(City found) {
        List<Weather> weathersNext = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        List<Weather> weathers = found.getWeathers();
        logger.info("weathers ->" + weathers.get(0).getDay().getDayOfYear());
        int dayAux = weathers.get(0).getDay().getDayOfYear(); //336
        for(Weather w: weathers){
            if(weathersNext.isEmpty()){
                weathersNext.add(w);
            }
            if(w.getDay().getDayOfYear() != dayAux){
                dayAux = w.getDay().getDayOfYear();
                String format = w.getDay().format(formatter);
                w.setStringDay(format);                
                weathersNext.add(w);
            }
        }
		return weathersNext;
	}

}
