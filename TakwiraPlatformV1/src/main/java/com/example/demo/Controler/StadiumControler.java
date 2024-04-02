package com.example.demo.Controler;

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

import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import com.example.demo.Entity.Stadium;
import com.example.demo.Entity.TimeSlot;

import java.util.Optional;
import org.springframework.http.MediaType;

import java.nio.file.Files;
import java.nio.file.Path;

import com.example.demo.Reposotory.StadiumRepo;
import com.example.demo.Reposotory.TimeSlotRepo;
import com.example.demo.Service.ComplexInitServiceImp;


@RestController
@RequestMapping("/api/stadium")
public class StadiumControler {
	
	@Autowired
	private final StadiumRepo stadiumRepo ; 
	
	@Autowired
    private ComplexInitServiceImp complexInitServiceImp;
	
	 @Autowired
	 private TimeSlotRepo timeSlotRepo;
	
	
	@Autowired
    public StadiumControler(StadiumRepo stadiumRepo) {
        this.stadiumRepo = stadiumRepo;
    }
	
	
	// Get all stadiums
    @GetMapping
    public ResponseEntity<List<Stadium>> getAllStadiums() {
        List<Stadium> stadiums = stadiumRepo.findAll();
        return new ResponseEntity<>(stadiums, HttpStatus.OK);
    }

    // Get a single stadium by ID
    @GetMapping("/{id}")
    public ResponseEntity<Stadium> getStadiumById(@PathVariable Long id) {
        Optional<Stadium> stadium = stadiumRepo.findById(id);
        return stadium.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Create a new stadium
    @PostMapping
    public ResponseEntity<Stadium> createStadium(@RequestBody Stadium stadium) {
        Stadium createdStadium = stadiumRepo.save(stadium);
        return new ResponseEntity<>(createdStadium, HttpStatus.CREATED);
    }

    // Update an existing stadium
    @PutMapping("/{id}")
    public ResponseEntity<Stadium> updateStadium(@PathVariable Long id, @RequestBody Stadium updatedStadium) {
        if (!stadiumRepo.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        updatedStadium.setId(id);
        Stadium savedStadium = stadiumRepo.save(updatedStadium);
        return new ResponseEntity<>(savedStadium, HttpStatus.OK);
    }

    // Delete a stadium by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteStadium(@PathVariable Long id) {
        if (!stadiumRepo.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        stadiumRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    
    
    @GetMapping(path = "/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] stadiumImage(@PathVariable(name = "id") Long id) throws Exception {
        Stadium stadium = stadiumRepo.findById(id).orElse(null);

        List<String> photoNames = stadium.getPhotos();
        if (!photoNames.isEmpty()) {
            String photoName = photoNames.get(0); // Get the first photo name
            File file = new File(System.getProperty("user.home") + "/takwira/images/" + photoName);
            Path path = Paths.get(file.toURI());
            return Files.readAllBytes(path);
        } else {
            // Handle the case where there are no photos for the stadium
            // You can return an appropriate response or throw an exception.
            return null;
        }
    }
 // Get stadiums by complex ID
    @GetMapping("/complex/{complexId}")
    public ResponseEntity<List<Stadium>> getStadiumsByComplex(@PathVariable Long complexId) {
        List<Stadium> stadiums = complexInitServiceImp.getStadiumsByComplexId(complexId);
        return new ResponseEntity<>(stadiums, HttpStatus.OK);
    }
    
 // get timeslots for stadium
    @GetMapping("/{stadiumId}/timeslots")
    public ResponseEntity<List<TimeSlot>> getTimeSlotsByStadiumId(@PathVariable Long stadiumId) {
        // Retrieve time slots for the specified stadiumId
        List<TimeSlot> timeSlots = timeSlotRepo.findTimeSlotsByStadiumId(stadiumId);

        if (timeSlots.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if no time slots found
        }

        return new ResponseEntity<>(timeSlots, HttpStatus.OK);
    }
   

}
