package com.example.demo.repositories;

import com.example.demo.Model.Weather;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeathersRepo extends CrudRepository<Weather,Long> {

    Weather findAllById(Long id);
}
