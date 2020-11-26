package com.example.demo.Controller;

import com.example.demo.Model.City;
import com.example.demo.Service.CityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.annotation.WebServlet;
import java.util.List;
import java.util.logging.Logger;

@Controller
@WebServlet
public class CityController {

    Logger logger = Logger.getLogger(
            CityController.class.getName());

   private CityService cityService;

    public CityController(CityService cityService){
        this.cityService = cityService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/cities")
    public String getAllCities(){
        return "showCities";
    }

    @GetMapping("/onlyNorth")
    public String getNorthCities(Model model){

        List<City> northCitites = this.cityService.findRegion("North");
        model.addAttribute("region", northCitites);

        return "regionCities";
    }

    @GetMapping("/onlyCenter")
    public String getCenterCities(Model model){

        List<City> centerCitites = this.cityService.findRegion("Center");
        model.addAttribute("region", centerCitites);

        return "regionCities";
    }

    @GetMapping("/onlySouth")
    public String getSouthCities(Model model){

        List<City> southCities = this.cityService.findRegion("South");
        model.addAttribute("region", southCities);

        return "regionCities";
    }

    @GetMapping("/onlyIsle")
    public String getIsleCities(Model model){

        List<City> isleCities = this.cityService.findRegion("Isle");
        model.addAttribute("region", isleCities);

        return "regionCities";
    }

    @GetMapping("/city")
    public String getCityweather(@RequestParam String name, Model model){
        City found = this.cityService.findByName(name);
        model.addAttribute("city", found);
        return "city";
    }



}
