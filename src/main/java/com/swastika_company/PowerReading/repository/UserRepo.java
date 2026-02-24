package com.swastika_company.PowerReading.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.swastika_company.PowerReading.dto.UserAndMeter;
import com.swastika_company.PowerReading.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {
	
	@Query("""
		    SELECT u FROM User u 
		    LEFT JOIN FETCH u.meter 
		    WHERE u.userName = :userName
		""")
		Optional<User> findByUserName(@Param("userName") String userName);
	
	@Query("""
		    SELECT new com.swastika_company.PowerReading.dto.User(u.userName, u.userPassword) 
		    FROM User u
		""")
		List<com.swastika_company.PowerReading.dto.User> findUserNamePassword();
}
