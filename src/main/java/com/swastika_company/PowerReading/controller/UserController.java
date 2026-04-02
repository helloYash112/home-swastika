package com.swastika_company.PowerReading.controller;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swastika_company.PowerReading.dto.MeterAndReading;
import com.swastika_company.PowerReading.dto.UserAndMeter;
import com.swastika_company.PowerReading.dto.UserDTO;
import com.swastika_company.PowerReading.dto.UserMeterDTO;
import com.swastika_company.PowerReading.service.UserService;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {
	/*
	 * POST /api/users → create a new user

GET /api/users/{id} → fetch a user by ID

GET /api/users → list all users

PUT /api/users/{id} → update a user

DELETE /api/users/{id} → delete a user*/

	private UserService service;

	public UserController(UserService service) {
		super();
		this.service = service;
	}
	/*
	 * find list of meter user have*/
	
	@GetMapping("/meter/{userName}")
	public UserAndMeter getMeterByName(@PathVariable("userName") String userName) {
		return service.getUserAndMeter(userName);
		
	}
	/*
	 * get user name and his password*/
	@GetMapping("/user/{userName}")
	public com.swastika_company.PowerReading.dto.User searchUserByName(@PathVariable("userName") String userName) {
		return service.getUserByName(userName);
		
	}
	/*
	 * get list of reading meter have*/
	@GetMapping("/reading/{meterName}")
	public List<MeterAndReading> getSelectedMeter(@PathVariable("meterName")String meterName) {
		return service.getMeterByName(meterName);
		
	}
	@GetMapping("/{id}")
	//public User getUserById(Long id)
	public UserDTO getUserById(@PathVariable("id")Long id) {
		return service.getUserById(id);
	}
	
	@GetMapping
	public List<UserDTO> getAllUsers(){
		return service.getListOfUsers();
	}

    @PostMapping
    public ResponseEntity<UserMeterDTO> createUser(@RequestBody UserMeterDTO userDTO) {
    UserMeterDTO resDto = service.createUser(userDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(resDto);
}

	

}
