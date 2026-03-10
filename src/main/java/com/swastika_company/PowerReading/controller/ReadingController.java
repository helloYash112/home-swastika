package com.swastika_company.PowerReading.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swastika_company.PowerReading.dto.UserMeterReading;
import com.swastika_company.PowerReading.service.MeterReadingSer;
@RestController
@RequestMapping("/reading")
public class ReadingController {
	private MeterReadingSer service;

	public ReadingController(MeterReadingSer service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/{id}")
	public List<UserMeterReading> getBuUserId(@PathVariable("id") Long id){
		return service.getReadingByUserId(id);
		
	}
	
	

}
