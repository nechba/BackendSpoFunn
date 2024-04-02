package com.example.demo.Entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
public class City implements Serializable {
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id ; 
	private String Name ; 
	private double Latitude, Longitude, Atitude ; 
	private int ReservationScoreCity; 
	
	@OneToMany(mappedBy = "city")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Collection<Complex> complexs; 

}
