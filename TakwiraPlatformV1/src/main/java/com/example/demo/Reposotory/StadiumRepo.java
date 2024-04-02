package com.example.demo.Reposotory;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.Entity.Reservation;
import com.example.demo.Entity.Stadium;
@RepositoryRestResource
@CrossOrigin("*") 
public interface StadiumRepo extends JpaRepository<Stadium, Long> {

	

}
