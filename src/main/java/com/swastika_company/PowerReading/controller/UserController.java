package com.swastika_company.PowerReading.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swastika_company.PowerReading.dto.MeterAndReading;
import com.swastika_company.PowerReading.dto.UserAndMeter;
import com.swastika_company.PowerReading.entity.Meter;
import com.swastika_company.PowerReading.entity.User;
import com.swastika_company.PowerReading.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private UserService service;

	public UserController(UserService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/meter/{userName}")
	public UserAndMeter getMeterByName(@PathVariable("userName") String userName) {
		return service.getUserAndMeter(userName);
		
	}
	
	@GetMapping("/user/{userName}")
	public com.swastika_company.PowerReading.dto.User searchUserByName(@PathVariable("userName") String userName) {
		return service.getUserByName(userName);
		
	}
	@GetMapping("/reading/{meterName}")
	public List<MeterAndReading> getSelectedMeter(@PathVariable("meterName")String meterName) {
		return service.getMeterByName(meterName);
		
	}
	

}
