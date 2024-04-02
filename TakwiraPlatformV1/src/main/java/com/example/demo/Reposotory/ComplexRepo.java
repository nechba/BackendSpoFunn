package com.example.demo.Reposotory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.Entity.Complex;

@RepositoryRestResource
@CrossOrigin("*") 
public interface ComplexRepo extends JpaRepository<Complex, Long> {
	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Complex c WHERE c.Name = :name")
    boolean existsByName(@Param("name") String name);
}
