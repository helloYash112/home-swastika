package com.swastika_company.PowerReading.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.swastika_company.PowerReading.dto.MeterDTO;
import com.swastika_company.PowerReading.dto.UserAndMeter;
import com.swastika_company.PowerReading.entity.User;
import com.swastika_company.PowerReading.repository.UserRepo;

@Service
public class UserService {
	protected UserRepo repo;

	public UserService(UserRepo repo) {
		super();
		this.repo = repo;
	}
	
	public void saveUser(User user) {
		repo.save(user);
		
	}
	
	public void deleteUser(User user) {
		repo.delete(user);
	}
	
	public UserAndMeter getUserAndMeter(String userName) {
	    Optional<User> userOpt = repo.findByUserName(userName);
	    
	    if (userOpt.isPresent()) {
	        User user = userOpt.get();
	        
	        // Transform the list of Meter entities into a list of MeterDTO records
	        List<MeterDTO> meterList = user.getMeter().stream()
	            .map(m -> new MeterDTO(
	                m.getMeterName(), 
	                m.getMeterNo(),   // Check: Does your entity have getMeterNo()?
	                m.getMacId()      // Check: Does your entity have getMacId()?
	            ))
	            .toList();
	            
	        return new UserAndMeter(user.getUserName(), meterList);
	    } else {
	        // Returns the object with an empty list if user not found
	        return new UserAndMeter(userName, List.of());
	    }
	}
}
