package com.example.demo.Controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

import com.example.demo.Entity.City;
import com.example.demo.Reposotory.CityRepo;

@RestController
@RequestMapping("/api/city")
public class CityController {
	 
	@Autowired
	private CityRepo cityRepo;

	
	 @GetMapping
	 public List<City> getAllCities() 
	 {
	  return cityRepo.findAll();
	 }
	 
	 // Get a specific city by ID
	    @GetMapping("/{id}")
	    public ResponseEntity<City> getCityById(@PathVariable Long id) {
	        Optional<City> city = cityRepo.findById(id);
	        return city.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	    }

	    // Create a new city
	    @PostMapping
	    public ResponseEntity<City> createCity(@RequestBody City city) {
	        City createdCity = cityRepo.save(city);
	        return ResponseEntity.status(HttpStatus.CREATED).body(createdCity);
	    }

	    // Update an existing city by ID
	    @PutMapping("/{id}")
	    public ResponseEntity<City> updateCity(@PathVariable Long id, @RequestBody City updatedCity) {
	        if (!cityRepo.existsById(id)) {
	            return ResponseEntity.notFound().build();
	        }
	        updatedCity.setId(id);
	        City savedCity = cityRepo.save(updatedCity);
	        return ResponseEntity.ok(savedCity);
	    }

	    // Delete a city by ID
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteCity(@PathVariable Long id) {
	        if (!cityRepo.existsById(id)) {
	            return ResponseEntity.notFound().build();
	        }
	        cityRepo.deleteById(id);
	        return ResponseEntity.noContent().build();
	    }

	 
}
