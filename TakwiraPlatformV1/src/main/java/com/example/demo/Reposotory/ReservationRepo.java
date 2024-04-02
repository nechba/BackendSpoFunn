	package com.example.demo.Reposotory;
	
	import java.util.List;
	
	import org.springframework.data.jpa.repository.JpaRepository;
	import org.springframework.data.jpa.repository.Query;
	import org.springframework.data.repository.query.Param;
	import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
	
	import com.example.demo.Entity.Reservation;


	@RepositoryRestResource
	@CrossOrigin("*") 
	public interface ReservationRepo extends JpaRepository<Reservation, Long> {
		List<Reservation> findByUserId(Long userId);
		
	

	}
	
	
