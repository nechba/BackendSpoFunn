package com.example.demo.Entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Stadium implements Serializable{
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ; 
	private String Name ; 
	private int NumberOfPlaces; 
	
	@ElementCollection
	@CollectionTable(name = "stadium_photos", joinColumns = @JoinColumn(name = "stadium_id"))
	private List<String> photos;
	
	private double price;  
	private int reservationScore; 
    private String discription;     
	
    @ManyToOne
    @JoinColumn(name= "Complex_id")
	private Complex complex ; 

	@ManyToMany
    @JoinTable(
        name = "stadium_category",
        joinColumns = @JoinColumn(name = "stadium_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> category;
	
	@OneToMany(mappedBy = "stadium")
	private Collection<TimeSlot> timeSlots ;


	
	



	
	
}
