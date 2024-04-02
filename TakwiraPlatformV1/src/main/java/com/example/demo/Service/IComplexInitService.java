package com.example.demo.Service;

import java.util.List;

import com.example.demo.Entity.Stadium;

public interface IComplexInitService {
	
	public void initCity()    ; 
	public void initComplex() ; 
	public void initStadium() ; 
	public void initcategory();
	List<Stadium> getStadiumsByComplexId(Long complexId); 
	public void initTimeSlots(); 
	public void initUser(); 
	public void initreservation(); 


}
