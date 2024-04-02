package com.example.demo.Controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import com.example.demo.Entity.Reservation;


import com.example.demo.Reposotory.ReservationRepo;
import com.example.demo.Reposotory.StadiumRepo;
import com.example.demo.Reposotory.TimeSlotRepo;
import com.example.demo.Reposotory.UserRepo;
import com.example.demo.Service.ReservationService;






@RestController
@RequestMapping("/api/reservation")
@CrossOrigin(origins = "http://localhost:4200") // Add this line
public class ReservationControler {
	
	
	@Autowired
	StadiumRepo stadiumRepo; 
	@Autowired
	UserRepo userRepo ;
	@Autowired
	TimeSlotRepo timeSlotRepo ; 
	@Autowired
    private ReservationService reservationService;

	

    private final  ReservationRepo reservationRepo;
    
    
    @Autowired
    public ReservationControler(ReservationRepo reservationRepo) {
        this.reservationRepo = reservationRepo;
    }
	

	 
	 
	 

    // Get all reservations
    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationRepo.findAll();
    }

    // Get a specific reservation by ID
    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
        Optional<Reservation> reservation = reservationRepo.findById(id);
        return reservation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new reservation
    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        Reservation createdReservation = reservationRepo.save(reservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReservation);
    }
    
    @PostMapping("/saveReservation")
    public ResponseEntity<String> savereservation(@RequestBody List<Reservation> empData) {
        reservationRepo.saveAll(empData);
        return ResponseEntity.ok("Reservation saved");
    }
    
     
   

    // Update an existing reservation by ID
    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long id, @RequestBody Reservation updatedReservation) {
        if (!reservationRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        updatedReservation.setId(id);
        Reservation savedReservation = reservationRepo.save(updatedReservation);
        return ResponseEntity.ok(savedReservation);
    }

    // Delete a reservation by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        if (!reservationRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        reservationRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Reservation>> getReservationsByUserId(@PathVariable Long userId) {
	    List<Reservation> reservations = reservationService.findReservationsByUserId(userId);
	
	    if (reservations.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	
	    return new ResponseEntity<>(reservations, HttpStatus.OK);
	}
	
	
	// Update the Statusreservation field for a specific reservation by ID
	@PutMapping("/{id}/status")
	public ResponseEntity<Reservation> updateReservationStatus(@PathVariable Long id, @RequestParam char Statusreservation) {
	    Optional<Reservation> optionalReservation = reservationRepo.findById(id);
	    if (optionalReservation.isPresent()) {
	        Reservation reservation = optionalReservation.get();
	        reservation.setStatusreservation(Statusreservation);
	        Reservation updatedReservation = reservationRepo.save(reservation);
	        return ResponseEntity.ok(updatedReservation);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	
	





    
 
    
    
  
    
    
    
}


