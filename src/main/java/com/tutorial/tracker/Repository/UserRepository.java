package com.tutorial.tracker.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tutorial.tracker.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByUserEmail(String email);
	
	

}
