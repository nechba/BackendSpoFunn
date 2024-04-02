package com.example.demo.Entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Reservation implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	private char Statusreservation ; 

    @ManyToOne
    @JoinColumn(name = "stadium_id")
    private Stadium stadium;
    
    @ManyToOne
    @JoinColumn(name= "user_id")
    private User user ; 
    
    @ManyToOne
    @JoinColumn(name= "TimeSlot_id")
    private TimeSlot timeSlot ; 
    
    @ManyToOne
    @JoinColumn(name = "complex_id") // Add this to link Reservation with Complex
    private Complex complex; // Add this field

	
	

	
	


}
