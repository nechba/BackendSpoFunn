package com.example.demo.Entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ; 
	private String Name,LastName ; 
	private boolean StatusAcount;
	private char Acount; 
    @Column(unique = true)
	private String Email ;
	private Long PhoneNumber ; 
	private String photo ; 
	private String password ; 
	private int ReservationScoreUser; 
	
   // @OneToMany(mappedBy = "user")
    //private Set<Reservation> reservations;
    


	

}
