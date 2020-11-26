package com.example.demo.Service;

import com.example.demo.Controller.CityController;
import com.example.demo.Model.City;
import com.example.demo.Repository.CityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityService {
    @Autowired
    public CityRepository cityRepo;

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
}
