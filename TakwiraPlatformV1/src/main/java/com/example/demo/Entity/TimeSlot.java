package com.example.demo.Entity;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class TimeSlot {
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ; 
    private LocalTime startTime;
    private LocalTime endTime;
	private boolean status;
	
	
	 //@ManyToOne
	 //@JoinColumn(name = "stadium_id")
	 //private Stadium stadium;
	 
	 
	 @ManyToOne
		@JsonProperty(access = Access.WRITE_ONLY)
		private Stadium stadium ; 

}
