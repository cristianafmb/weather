package com.example.demo.Controller;

import java.security.KeyStore.Entry;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import com.example.demo.Model.City;
import com.example.demo.Model.Weather;
import com.example.demo.Service.CityService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

        LocalDate t = LocalDate.now();

        model.addAttribute("day", t);
        return "city";
    }

    @PostMapping("/city")
    public String degrees(HttpServletRequest request){
        Long c = Long.valueOf(request.getParameter("celsius"));
        Long f = Long.valueOf(request.getParameter("fahr"));

        if(c!=null){
            this.cityService.setCelsius();
        }
        if(f!=null){
            this.cityService.setFahrenheit();
        }
        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }

    @GetMapping("/today")
    public String getTodayWeather(Model model) {

        Map<String, Weather> citiesWeather = this.cityService.todayWeather();
       

        model.addAttribute("map", citiesWeather);

        return "todayAllCities";
    }

}
