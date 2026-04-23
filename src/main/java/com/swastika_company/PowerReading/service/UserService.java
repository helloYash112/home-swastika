package com.swastika_company.PowerReading.service;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import com.swastika_company.PowerReading.dto.MeterAndReading;
import com.swastika_company.PowerReading.dto.MeterDTO;
import com.swastika_company.PowerReading.dto.MeterResDTO;
import com.swastika_company.PowerReading.dto.ReadingDTO;
import com.swastika_company.PowerReading.dto.UserANdMeterDTO;
import com.swastika_company.PowerReading.dto.UserAndMeter;
import com.swastika_company.PowerReading.dto.UserDTO;
import com.swastika_company.PowerReading.dto.UserDTO1;
import com.swastika_company.PowerReading.dto.UserMeterDTO;
import com.swastika_company.PowerReading.dto.UserResDTO;
import com.swastika_company.PowerReading.entity.Meter;
import com.swastika_company.PowerReading.entity.MeterReading;
import com.swastika_company.PowerReading.entity.User;
import com.swastika_company.PowerReading.repository.UserRepo;

@Service
public class UserService {
	protected UserRepo repo;
	@Autowired
	protected MeterReadingSer mrs;

	public UserService(UserRepo repo) {
		super();
		this.repo = repo;
	}
	
	public User saveUser(User user) {
		return repo.save(user);
		
	}
	public void deleteByID(Long id) {
		repo.deleteById(id);
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
	//public User findByUserId(@Param("id")Long id);
	public UserDTO getUserById(Long id) {
		return repo.findByUserId(id);
	}
	public List<UserDTO> getListOfUsers(){
		return repo.getAllUser();
	}
	
	public UserResDTO createUser(UserDTO1 userDTO) {
		String userName=userDTO.userName();
		String userPassword=userDTO.userPassword();
		//checking is user exits or not
		boolean userExists=repo.existsByEmailAndPassword(userName, userPassword);
		if (userExists) {
			throw new DuplicateKeyException("User with email " + userName+"or"+userPassword + " already exists");
		}
		User user=new User();
		user.setUserName(userDTO.userName());
		user.setuserPassword(userDTO.userPassword());
		
		User resUser = repo.save(user);
		UserResDTO res=new UserResDTO(resUser.getId(),resUser.getUserName(),List.of());

		return res;
		
	}
	public UserResDTO getByUsernameAndPassword(String userName, String userPassword) {
	    User u = repo.findByUsernameAndPassword(userName, userPassword);

	    if (u == null) {
	        
	        throw new RuntimeException("User not found with given credentials");
	    }
       /*public record ReadingDTO(Long rid,LocalDate date,LocalTime time,double kwh,float pf) {
}      */
	    
	    List<MeterResDTO> meters = new ArrayList<>();
	    if (u.getMeter() != null && !u.getMeter().isEmpty()) {
	       meters=u.getMeter().stream().map(meter ->{
	    	   //getting list of current month reading 
	    	   List<ReadingDTO> readings=mrs.getData(meter.getMeterId(), null, null);
	    	 
	    	   return new MeterResDTO(meter.getMeterId(),meter.getMeterName(),readings);
	    	 
	       }
	    	   ).toList();
	       }
	    

	    return new UserResDTO(u.getId(),u.getUserName(),meters);
	}


}
