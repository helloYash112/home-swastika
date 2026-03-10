package com.swastika_company.PowerReading.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swastika_company.PowerReading.dto.MeterDTO;
import com.swastika_company.PowerReading.service.MeterService;

@RestController
@RequestMapping("/api/meters")
public class MeterController {
	private MeterService  ser;

	public MeterController(MeterService ser) {
		super();
		this.ser = ser;
	}
	
	@GetMapping
	public List<MeterDTO> getAllMeter(){
		return ser.getMeters();
		
	}
	@GetMapping("/{id}")
	public MeterDTO getMeterById(@PathVariable("id") Long id) {
		return ser.getMeterById(id);
		
	}
	

}
