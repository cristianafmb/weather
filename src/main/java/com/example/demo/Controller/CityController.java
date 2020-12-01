package com.example.demo.Controller;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import com.example.demo.Model.City;
import com.example.demo.Model.Weather;
import com.example.demo.Service.CityService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@WebServlet
public class CityController {

    Logger logger = Logger.getLogger(CityController.class.getName());

    private CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/cities")
    public String getAllCities() {
        return "showCities";
    }

    @GetMapping("/onlyNorth")
    public String getNorthCities(Model model) {

        List<City> northCitites = this.cityService.findRegion("North");
        model.addAttribute("region", northCitites);

        return "regionCities";
    }

    @GetMapping("/onlyCenter")
    public String getCenterCities(Model model) {

        List<City> centerCitites = this.cityService.findRegion("Center");
        model.addAttribute("region", centerCitites);

        return "regionCities";
    }

    @GetMapping("/onlySouth")
    public String getSouthCities(Model model) {

        List<City> southCities = this.cityService.findRegion("South");
        model.addAttribute("region", southCities);

        return "regionCities";
    }

    @GetMapping("/onlyIsle")
    public String getIsleCities(Model model) {

        List<City> isleCities = this.cityService.findRegion("Isle");
        model.addAttribute("region", isleCities);

        return "regionCities";
    }

    @GetMapping("/city")
    public String getCityweather(@RequestParam String name, Model model) {
        City found = this.cityService.findByName(name);
        model.addAttribute("city", found);
        Weather today = this.cityService.weatherToday(found);
        model.addAttribute("today", today);

        List<Weather> weathers = this.cityService.variationWeather(found);
       
        Integer init = weathers.get(0).getDay().getHour(); 
        Integer fin = weathers.get(weathers.size()-1).getDay().getHour(); 
        ArrayList<Integer> hours = new ArrayList<>();
        int sub = fin - init;
        int ratio = sub / weathers.size();
        int j = init;
        for(j=init; j<fin; j++){
            hours.add(j);
            j = j-1 + ratio;
        }
       
        
        model.addAttribute("hours", hours);


        ArrayList<Double> atual = new ArrayList<>();

        ArrayList<Integer> atualInt = new ArrayList<>();
        int i = 0;
        for (Weather w : weathers) {
            atual.add(w.getAtualDegree());
        }

        model.addAttribute("size", atual.size());
        for (i = 0; i < atual.size(); i++) {
            Integer d = (int) Math.round(atual.get(i));
            atualInt.add(d);

        }
        model.addAttribute("AI", atualInt);
        logger.info("\n\n hours-> " + hours + "\n temperatures->" + atualInt);


        return "city";
    }

    @GetMapping("/fcity")
    public String degrees(@RequestParam String name, HttpServletRequest request, Model model) {

        this.cityService.setFahrenheit();
        City found = this.cityService.findByName(name);
        model.addAttribute("city", found);
        Weather today = this.cityService.weatherToday(found);
        model.addAttribute("today", today);

        return "city";
    }

    @GetMapping("/today")
    public String getTodayWeather(Model model) {

        Map<String, Weather> citiesWeather = this.cityService.todayWeather();

        model.addAttribute("map", citiesWeather);

        return "todayAllCities";
    }

    @GetMapping("/nextdays")
    public String nextDays(Model model) {
        Map<String, Weather> citiesWeather = this.cityService.todayWeather();

        model.addAttribute("map", citiesWeather);
        return "choosedays";
    }

    @GetMapping("/next")
    public String nextDaysWeather(@RequestParam String city, @RequestParam Integer days, Model model) {

        City cityFound = this.cityService.findByName(city);
        model.addAttribute("city", city);
        List<Weather> weathers = new ArrayList<>();

        weathers = this.cityService.nextDays(cityFound);

        
        logger.info("msg" + weathers);
        model.addAttribute("weather", weathers);

        return "ndays";
    }

   
}
