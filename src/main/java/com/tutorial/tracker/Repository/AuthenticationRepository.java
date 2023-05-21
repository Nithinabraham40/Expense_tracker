package com.tutorial.tracker.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tutorial.tracker.model.Authentication;

public interface AuthenticationRepository extends JpaRepository<Authentication, Long>{

	Authentication findByToken(String token);

	//Authentication findFirstByToken(String token);

	
	@Query(
			
			value="select * from tbl_token where token=:token",
			nativeQuery = true
			)
	
	
	Authentication findbyToken(String token);
	
	

}
