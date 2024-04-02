package com.example.demo.Reposotory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.example.demo.Entity.User;

@RepositoryRestResource
@CrossOrigin("*") 
public interface UserRepo extends JpaRepository<User, Long> {
	@Query(value = "SELECT * FROM User u WHERE u.email = ?1 AND u.password = ?2", nativeQuery = true)
    User findUserByEmailAndPassword(String email, String password);
	@Query(value = "SELECT * FROM User u WHERE u.email = ?1", nativeQuery = true)
	User findByEmail(String email);
}
