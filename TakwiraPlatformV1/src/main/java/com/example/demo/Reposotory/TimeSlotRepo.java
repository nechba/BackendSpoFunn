package com.example.demo.Reposotory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.Entity.City;
import com.example.demo.Entity.TimeSlot;

@RepositoryRestResource
@CrossOrigin("*") 
public interface TimeSlotRepo extends JpaRepository<TimeSlot, Long> {
    @Query("SELECT t FROM TimeSlot t WHERE t.stadium.id = :stadiumId")
    List<TimeSlot> findTimeSlotsByStadiumId(@Param("stadiumId") Long stadiumId);
}
