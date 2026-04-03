package com.swastika_company.PowerReading.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.swastika_company.PowerReading.dto.UserDTO;
import com.swastika_company.PowerReading.entity.Meter;
import com.swastika_company.PowerReading.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {
	/*
	 * 
	 * GET /api/users → list all users

      GET /api/users/{id} → get one user

       POST /api/users → create user
*/
	@Query("""
		    SELECT u FROM User u 
		    LEFT JOIN FETCH u.meter 
		    WHERE u.userName = :userName
		""")
		Optional<User> findByUserName(@Param("userName") String userName);
	
	@Query("""
		    SELECT new com.swastika_company.PowerReading.dto.User(u.userName, u.userPassword) 
		    FROM User u where u.userName =:userName
		""")
		Optional<com.swastika_company.PowerReading.dto.User> findUserNamePassword(@Param("userName") String userName);
	
	@Query(""" 
			
			SELECT m FROM User u JOIN u.meter m WHERE m.meterName = :meterName """)
	    Meter findMeterByName(@Param("meterName") String meterName);
	
	@Query("SELECT new com.swastika_company.PowerReading.dto.UserDTO(u.id,u.userName,u.userPassword) from User u where u.id =:id")
	public UserDTO  findByUserId(@Param("id")Long id);
	@Query("SELECT new com.swastika_company.PowerReading.dto.UserDTO(u.id,u.userName,u.userPassword) from User u")
	public List<UserDTO> getAllUser();
	
	@Query("SELECT u FROM User u WHERE u.userName = :username AND u.userPassword = :password")
	User findByUsernameAndPassword(@Param("username") String username,
	                               @Param("password") String password);

}
