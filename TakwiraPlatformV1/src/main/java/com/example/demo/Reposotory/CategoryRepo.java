package com.example.demo.Reposotory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.Entity.Category;

@RepositoryRestResource
@CrossOrigin("*") 
public interface CategoryRepo extends JpaRepository<Category, Long> {
	 @Query(value = "SELECT id FROM Category ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Long findRandomCategoryId();

}
