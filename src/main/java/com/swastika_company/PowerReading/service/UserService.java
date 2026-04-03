package com.swastika_company.PowerReading.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.swastika_company.PowerReading.dto.MeterAndReading;
import com.swastika_company.PowerReading.dto.MeterDTO;
import com.swastika_company.PowerReading.dto.UserANdMeterDTO;
import com.swastika_company.PowerReading.dto.UserAndMeter;
import com.swastika_company.PowerReading.dto.UserDTO;
import com.swastika_company.PowerReading.dto.UserMeterDTO;
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
	
	public UserMeterDTO createUser(UserMeterDTO userDTO) {
		User user=new User();
		user.setUserName(userDTO.userName());
		user.setuserPassword(userDTO.userPassword());
		if(userDTO.meter() != null) {
			List<Meter> meters=userDTO.meter().stream().map(dto ->{
				Meter m=new Meter();
				m.setMacId(dto.meterMacAddress());
				m.setMeterName(dto.meterName());
				m.setMeterNo(dto.meterNumber());
				m.setUser(user);
				return m;
			}).toList();
			user.setMeter(meters);
       }
		User resUser = repo.save(user);

		UserMeterDTO resDto = new UserMeterDTO(
		    resUser.getUserName(),
		    resUser.getuserPassword(),   // ✅ fixed method name
		    resUser.getMeter().stream()
		        .map(m -> new MeterDTO(
		            m.getMeterName(),
		            m.getMeterNo(),
		            m.getMacId()
		        ))
		        .toList()   // or .collect(Collectors.toList()) if < Java 16
		);
		
		return resDto;
		
	}
	public UserANdMeterDTO getByUsernameAndPassword(String userName, String userPassword) {
	    User u = repo.findByUsernameAndPassword(userName, userPassword);

	    if (u == null) {
	        
	        throw new RuntimeException("User not found with given credentials");
	    }

	    String name = u.getUserName();
	    
	    List<MeterDTO> meters = new ArrayList<>();
	    if (u.getMeter() != null && !u.getMeter().isEmpty()) {
	        meters = u.getMeter().stream()
	                  .map(m -> new MeterDTO(m.getMeterName(), m.getMacId(), m.getMeterNo()))
	                  .toList();
	    }

	    return new UserANdMeterDTO(name, meters);
	}


}
