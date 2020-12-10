package com.example.demo.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import com.example.demo.Model.City;
import com.example.demo.Model.Weather;
import com.example.demo.Service.CityService;

import org.apache.logging.log4j.message.ReusableMessage;
import org.springframework.http.ResponseEntity;
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

    /**
     * Home page with all the options
     * 
     * @return
     */
    @GetMapping("/")
    public String home() {
        return "home";
    }

    /**
     * Page that show the map with all cities, it's a map with coordenates that the
     * user can click on There is also a filter, that divide the country in "North",
     * "Center", "South" and "Isles"
     * 
     * @return
     */
    @GetMapping("/cities")
    public ResponseEntity<Iterable<City>> getAllCities() {
        return ResponseEntity.ok(this.cityService.findAll());
    }

    /**
     * This is when the user choose by the filter and click on North Show all the
     * North cities
     */
    @GetMapping("/onlyNorth")
    public ResponseEntity<Iterable<City>> getNorthCities(Model model) {
        return ResponseEntity.ok(this.cityService.findRegion("North"));
    }

    /**
     * This is when the user choose by the filter and click on Center Show all the
     * Center cities
     */
    @GetMapping("/onlyCenter")
    public ResponseEntity<Iterable<City>> getCenterCities(Model model) {
        return ResponseEntity.ok(this.cityService.findRegion("Center"));
    }

    /**
     * This is when the user choose by the filter and click on South Show all the
     * South cities
     */
    @GetMapping("/onlySouth")
    public ResponseEntity<Iterable<City>> getSouthCities(Model model) {
        return ResponseEntity.ok(this.cityService.findRegion("South"));
    }

    /**
     * This is when the user choose by the filter and click on Isles Show all the
     * ISles cities
     */
    @GetMapping("/onlyIsle")
    public  ResponseEntity<Iterable<City>> getIsleCities(Model model) {
        return ResponseEntity.ok(this.cityService.findRegion("Isle"));
    }

    /**
     * City when the name is given in the url, like /city?name=Vila Real
     * It's show the name of the city, the atual Degree
     * The div with all the information (min, max, precipitation, humidy, wind)
     * And the chart with the Today Temperature
     */
    @GetMapping("/city")
    public ResponseEntity<ArrayList<Object>> getCityweather(@RequestParam String name, Model model) {
        City found = this.cityService.findByName(name);
        Weather today = this.cityService.weatherToday(found);
    

        List<Weather> weathers = this.cityService.variationWeather(found);

        Integer init = weathers.get(0).getDay().getHour();
        Integer fin = weathers.get(weathers.size() - 1).getDay().getHour();
        ArrayList<Integer> hours = new ArrayList<>();
        int sub = fin - init;
        int ratio = sub / weathers.size();
        if (ratio <= 0)
            ratio = 1;
        int j;

        for (j = init - 1; j < fin; j++) {
            hours.add(j);
            j = j - 1 + ratio;
        }


        ArrayList<Double> atual = new ArrayList<>();

        ArrayList<Integer> atualInt = new ArrayList<>();
        int i = 0;
        for (Weather w : weathers) {
            atual.add(w.getAtualDegree());
        }

        for (i = 0; i < atual.size(); i++) {
            Integer d = (int) Math.round(atual.get(i));
            atualInt.add(d);

        }
        ArrayList<Object> all = new ArrayList<>();
        all.add(found);
        all.add(today);
        all.add(atual);
        return ResponseEntity.ok(all);
    }



    /**
     * show all the infomation (city, day, min, max, precipitation, humidity, wind)
     * of all the cities
     * @param model
     * @return
     */
    @GetMapping("/today")
    public  ResponseEntity<Map<String, Weather>> getTodayWeather(Model model) {

        Map<String, Weather> citiesWeather = this.cityService.todayWeather();
        return ResponseEntity.ok(citiesWeather);
    }

    /**
     * form to choose the city and the number of days to see
     * @param model
     * @return
     */
    @GetMapping("/nextdays")
    public ResponseEntity<Map<String, Weather>> nextDays(Model model) {
        Map<String, Weather> citiesWeather = this.cityService.todayWeather();
        return ResponseEntity.ok(citiesWeather);
    }

    /**
     * search for the city, search for the weathers of that city
     * and then display the number of days with the information of the city
     * @param city the name of the city to see the weather
     * @param days how many days to see the weather - with max = 5
     * @param model
     * @return
     */
    @GetMapping("/next")
    public ResponseEntity<ArrayList<Object>> nextDaysWeather(@RequestParam String city, @RequestParam Integer days,
            Model model) {

        City cityFound = this.cityService.findByName(city);
        model.addAttribute("city", city);
        List<Weather> weathers = new ArrayList<>();

        weathers = this.cityService.nextDays(cityFound, days);

        ArrayList<Object> all = new ArrayList<>();
        all.add(cityFound);
        all.add(weathers);

        return ResponseEntity.ok(all);
    }

}
