package com.example.demo.repositories;

import com.example.demo.Model.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitiesRepo extends CrudRepository<City,Long> {

    City findAllById(Long id);
}
