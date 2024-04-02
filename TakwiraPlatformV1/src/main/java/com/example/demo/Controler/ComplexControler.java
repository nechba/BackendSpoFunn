package com.example.demo.Controler;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Category;
import com.example.demo.Entity.Complex;
import com.example.demo.Reposotory.ComplexRepo;
import java.util.Optional;

@RestController
@RequestMapping("/api/complex")
public class ComplexControler {
 
	@Autowired
	private final ComplexRepo complexRepo;

    @Autowired
    public ComplexControler(ComplexRepo complexRepo) {
        this.complexRepo = complexRepo;
    }
    
    

    // Create a new Complex
    @PostMapping("/")
    public ResponseEntity<Complex> createComplex(@RequestBody Complex complex) {
        Complex savedComplex = complexRepo.save(complex);
        return new ResponseEntity<>(savedComplex, HttpStatus.CREATED);
    }

   
    
    @GetMapping
	 public List<Complex> getAllComplexs() 
	 {
	  return complexRepo.findAll();
	 }

    // Retrieve a Complex by ID
    @GetMapping("/{id}")
    public ResponseEntity<Complex> getComplexById(@PathVariable Long id) {
        Optional<Complex> complex = complexRepo.findById(id);
        if (complex.isPresent()) {
            return new ResponseEntity<>(complex.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update a Complex by ID
    @PutMapping("/{id}")
    public ResponseEntity<Complex> updateComplex(@PathVariable Long id, @RequestBody Complex complexDetails) {
        Optional<Complex> existingComplex = complexRepo.findById(id);
        if (existingComplex.isPresent()) {
            Complex complex = existingComplex.get();
            complex.setName(complexDetails.getName());
            complex.setStadiumNumber(complexDetails.getStadiumNumber());
            // Update other fields as needed
            Complex updatedComplex = complexRepo.save(complex);
            return new ResponseEntity<>(updatedComplex, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a Complex by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComplex(@PathVariable Long id) {
        Optional<Complex> complex = complexRepo.findById(id);
        if (complex.isPresent()) {
        	complexRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping(path = "/imageComplex/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] complexImage(@PathVariable(name = "id") Long id) throws Exception {
        Complex complex = complexRepo.findById(id).orElse(null);

        if (complex != null) {
            String photoName = complex.getPhoto();
            File file = new File(System.getProperty("user.home") + "/takwira/images/" + photoName);
            Path path = Paths.get(file.toURI());
            return Files.readAllBytes(path);
        } else {
            // Handle the case where the Complex with the given ID is not found
            // You can return an appropriate response or throw an exception.
            return null;
        }
    }
    
    @GetMapping("/{id}/category")
    public ResponseEntity<Category> getComplexCategory(@PathVariable Long id) {
        Optional<Complex> existingComplex = complexRepo.findById(id);
        if (existingComplex.isPresent()) {
            Complex complex = existingComplex.get();
            Category category = complex.getCategory();
            if (category != null) {
                return new ResponseEntity<>(category, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}





	

