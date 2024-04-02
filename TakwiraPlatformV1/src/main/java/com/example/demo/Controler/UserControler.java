package com.example.demo.Controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.example.demo.Entity.User;
import com.example.demo.Reposotory.UserRepo;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200") // Add this line

public class UserControler {
	
	@Autowired
    private UserRepo userRepo;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepo.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userRepo.findById(id).orElse(null);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userRepo.save(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        if (!userRepo.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.setId(id); // Ensure the ID is set to the correct value
        User updatedUser = userRepo.save(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (!userRepo.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/login")
    public ResponseEntity<User> loginUser(@RequestParam String email, @RequestParam String password) {
        User user = userRepo.findUserByEmailAndPassword(email, password);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); 
            	
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<Void> updateStatusAccount(@PathVariable Long id, @RequestParam boolean statusAccount) {
        User user = userRepo.findById(id).orElse(null);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        // Update the statusAccount field
        user.setStatusAcount(statusAccount);
        
        // Save the updated user
        userRepo.save(user);
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @GetMapping("/user-info")
    public ResponseEntity<User> findUserByEmail(@RequestParam String email) {
        User user = userRepo.findByEmail(email);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    
    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        // Implement logic to clear the user's session or token
        // Return a successful response
        return new ResponseEntity<>(HttpStatus.OK);
    }

}


