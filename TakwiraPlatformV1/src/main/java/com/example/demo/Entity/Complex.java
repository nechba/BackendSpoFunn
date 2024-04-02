package com.example.demo.Entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@javax.persistence.Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Complex  implements Serializable{

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id ; 
	private String Name ; 
	private int StadiumNumber ; 
	private String photo; 
	private int ReservationScoreComplex; 
	
	//@OneToMany(mappedBy = "complex")
	//private Collection<Stadium> stadiums ;
	 // Define the many-to-many relationship with Stadium
    
	
	@ManyToOne
	private City city ; 

	@ManyToMany(mappedBy = "complex")
	private Set<Partner> partners;
	
	@ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
	

}
