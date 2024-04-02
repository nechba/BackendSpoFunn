package com.example.demo.Controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.TimeSlot;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

import com.example.demo.Reposotory.TimeSlotRepo;

@RestController
@RequestMapping("/api/timeSlot")
public class TimeSlotCotroler {
	
	@Autowired
	TimeSlotRepo timeSlotRepo; 
	
	@GetMapping
    public List<TimeSlot> getAllTimeSlots() {
        return timeSlotRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimeSlot> getTimeSlotById(@PathVariable Long id) {
        Optional<TimeSlot> timeSlot = timeSlotRepo.findById(id);
        return timeSlot.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TimeSlot> createTimeSlot(@RequestBody TimeSlot timeSlot) {
        TimeSlot createdTimeSlot = timeSlotRepo.save(timeSlot);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTimeSlot);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TimeSlot> updateTimeSlot(@PathVariable Long id, @RequestBody TimeSlot updatedTimeSlot) {
        if (!timeSlotRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        updatedTimeSlot.setId(id);
        TimeSlot savedTimeSlot = timeSlotRepo.save(updatedTimeSlot);
        return ResponseEntity.ok(savedTimeSlot);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTimeSlot(@PathVariable Long id) {
        if (!timeSlotRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        timeSlotRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
