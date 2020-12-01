package com.example.demo.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.IntStream;

import com.example.demo.Controller.CityController;
import com.example.demo.Model.City;
import com.example.demo.Model.Weather;
import com.example.demo.Repository.CityRepository;
import com.example.demo.Repository.WeatherRepository;

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

    /**
     * search and return all the cities in the repository
     * @return
     */
    public List<City> findAll() {
        List<City> listcities = new ArrayList<>();
        this.cityRepo.findAll().forEach(listcities::add);
        return listcities;
    }

    /**
     * search cities by region, if the user is looking for only the North cities
     * It will return all the cities in the North
     * @param region
     * @return
     */
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

    /**
     * Find the city by Name
     * @param name
     * @return
     */
    public City findByName(String name) {
        List<City> allcities = this.findAll();
        for (City c : allcities) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Objective: get weather of city with the nearest hour as now()
     * It will look for every city and the weather, and check if it is the same day
     * If is the same day, add to the todayWeathers
     * after that is looking for the nearest hour
     * @param found
     * @return
     */
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

    /**
     * looking for the nearest hour by comparate the day-hour
     * @param hour
     * @param todayWeathers
     * @return
     */
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
        b = false;
        return null;
    }

    /**
     * Never Used - INCOMPLETE
     * to change fahrenheit to celsius
     */
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

    /**
     * Never Used - INCOMPLETE
     * to change celsius to fahrenheit
     */
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

    /**
     * get all the weather of today, of all the cities and return a treemap - order
     */
    public Map<String, Weather> todayWeather() {
        List<City> allCities = this.findAll();

        allCities.sort(Comparator.comparing(City::getName));

        TreeMap<String, Weather> cWeather = new TreeMap<>();
        for (City c : allCities) {
            cWeather.put(c.getName(), c.getWeathers().get(0));
        }

        return cWeather;
    }

    /**
     * to the chart
     * get all the temperature of the day, and return all the weather of today
     * @param found
     * @return
     */
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

    /**
     * by an n numbers of days, get all the weathers and return only the n days, starting today 
     * @param found
     * @param days
     * @return
     */
    public List<Weather> nextDays(City found, Integer days) {
        logger.info("days ->" + days);
        ArrayList<Weather> weathersNext = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        List<Weather> weathers = found.getWeathers();
        logger.info("weathers ->" + weathers.get(0).getDay().getDayOfYear());
        int dayAux = weathers.get(0).getDay().getDayOfYear(); // 336
        int i = 0;

        for (Weather w : weathers) {
            if (i == days) {
                break;
            }
            if (weathersNext.isEmpty()) {
                weathersNext.add(w);
                i++;
            }
            if (w.getDay().getDayOfYear() != dayAux) {
                dayAux = w.getDay().getDayOfYear();
                String format = w.getDay().format(formatter);
                w.setStringDay(format);
                weathersNext.add(w);
                i++;
            }

        }

        return weathersNext;
    }

}
