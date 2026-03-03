package com.swastika_company.PowerReading.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.swastika_company.PowerReading.dto.MeterAndReading;
import com.swastika_company.PowerReading.dto.MeterDTO;
import com.swastika_company.PowerReading.dto.UserAndMeter;
import com.swastika_company.PowerReading.entity.Meter;
import com.swastika_company.PowerReading.entity.MeterReading;
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
	
	public com.swastika_company.PowerReading.dto.User getUserByName(String name){
		Optional<com.swastika_company.PowerReading.dto.User> user=repo.findUserNamePassword(name);
		if(user.isPresent()) {
			return user.get();
		}
		else 
			return null;
	}
	
	public List<MeterAndReading> getMeterByName(String meterName) {
	    Meter m = repo.findMeterByName(meterName);
	    List<MeterReading> readings = m.getMeterReading();

	    List<MeterAndReading> currentReading = new ArrayList<>();
	    for (MeterReading read : readings) {
	        currentReading.add(
	            new MeterAndReading(
	                read.getDate(),
	                read.getTime(),
	                read.getKwh(),
	                read.getPf(),
	                m.getMacId(),
	                m.getMeterName()
	            )
	        );
	    }

	    return currentReading; // return empty list if no readings
	}

}
