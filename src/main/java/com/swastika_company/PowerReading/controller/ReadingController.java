package com.swastika_company.PowerReading.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swastika_company.PowerReading.dto.MeterAndReading;
import com.swastika_company.PowerReading.dto.ReadingDTO;
import com.swastika_company.PowerReading.dto.UserMeterReading;
import com.swastika_company.PowerReading.service.MeterReadingSer;
@RestController
@RequestMapping("/api/reading")
public class ReadingController {
	private MeterReadingSer service;

	public ReadingController(MeterReadingSer service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/user/{id}")
	public List<UserMeterReading> getByUserId(@PathVariable("id") Long id){
		return service.getReadingByUserId(id);
		
	}
	@GetMapping("/meter/{id}")
	public List<ReadingDTO> getReadingsById(@PathVariable long id){
		return service.getById(id);
	}
	
	@GetMapping("/{id}")
	public ReadingDTO getById(@PathVariable Long id) {
		return service.getReadingById(id);
	}
	@GetMapping
	public List<MeterAndReading> getAllReading(){
		return service.fetchAllMeterAndReading();
	}
	
	

}
