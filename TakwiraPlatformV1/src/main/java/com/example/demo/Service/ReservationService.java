package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Reservation;
import com.example.demo.Reposotory.ReservationRepo;

@Service
public class ReservationService {
	   @Autowired
	    private ReservationRepo reservationRepo;

	    public List<Reservation> findReservationsByUserId(Long userId) {
	        return reservationRepo.findByUserId(userId);
	    }

}
