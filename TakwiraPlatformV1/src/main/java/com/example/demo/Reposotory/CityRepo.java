package com.example.demo.Reposotory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.Entity.City;

@RepositoryRestResource
@CrossOrigin("*") 
public interface CityRepo extends JpaRepository<City, Long>{

}
