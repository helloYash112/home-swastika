package com.swastika_company.PowerReading.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.swastika_company.PowerReading.dto.UserAndMeter;


import com.swastika_company.PowerReading.service.UserService;

@RestController
@RequestMapping("/controller/users")
public class UserController {

	private UserService service;

	public UserController(UserService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/{userName}")
	public UserAndMeter getMeterByName(@PathVariable("userName") String userName) {
		return service.getUserAndMeter(userName);
		
	}

}
