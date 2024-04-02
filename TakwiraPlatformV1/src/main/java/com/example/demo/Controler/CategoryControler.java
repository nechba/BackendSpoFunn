package com.example.demo.Controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import com.example.demo.Entity.Category;
import com.example.demo.Entity.Reservation;
import com.example.demo.Reposotory.CategoryRepo;
import com.example.demo.Service.ReservationService;

@RestController
@RequestMapping("/api/category")
public class CategoryControler {
	   @Autowired
	    private CategoryRepo categoryRepo;
	   
	    

	    // Get all categories
	    @GetMapping
	    public List<Category> getAllCategories() {
	        return categoryRepo.findAll();
	    }

	    // Get a specific category by ID
	    @GetMapping("/{id}")
	    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
	        Optional<Category> category = categoryRepo.findById(id);
	        return category.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	    }

	    // Create a new category
	    @PostMapping
	    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
	        Category createdCategory = categoryRepo.save(category);
	        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
	    }

	    // Update an existing category by ID
	    @PutMapping("/{id}")
	    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category updatedCategory) {
	        if (!categoryRepo.existsById(id)) {
	            return ResponseEntity.notFound().build();
	        }
	        updatedCategory.setId(id);
	        Category savedCategory = categoryRepo.save(updatedCategory);
	        return ResponseEntity.ok(savedCategory);
	    }

	    // Delete a category by ID
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
	        if (!categoryRepo.existsById(id)) {
	            return ResponseEntity.notFound().build();
	        }
	        categoryRepo.deleteById(id);
	        return ResponseEntity.noContent().build();
	    }
	

	
		
}


