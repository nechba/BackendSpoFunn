package com.example.demo.Entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Partner {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id ; 
	private String Name,LastName ; 
	@Column(unique = true)
	private String Email ;
	private Long PhoneNumber ; 
	private String photo ; 
    private String password;
    private char accountType; // Char to represent the type of account
    private boolean status;
    private int ReservationScorePartnair; 
    
    @ManyToMany
    @JoinTable(
        name = "partner_complex",
        joinColumns = @JoinColumn(name = "partner_id"),
        inverseJoinColumns = @JoinColumn(name = "complex_id")
    )
    private Set<Complex> complex;
    

}
