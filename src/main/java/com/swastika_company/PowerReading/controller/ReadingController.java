package com.swastika_company.PowerReading.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swastika_company.PowerReading.dto.MeterAndReading;
import com.swastika_company.PowerReading.dto.ReadingDTO;
import com.swastika_company.PowerReading.dto.ReadingEntity;
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
	public List<ReadingEntity> getReadingsById(@PathVariable long id){
		return service.getById(id);
	}
	
	@GetMapping("/{id}")
	public ReadingEntity getById(@PathVariable Long id) {
		return service.getReadingById(id);
	}
	@GetMapping
	public List<MeterAndReading> getAllReading(){
		return service.fetchAllMeterAndReading();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> removeById(@PathVariable Long id) {
	    service.deleteById(id);
	    return ResponseEntity.noContent().build();
	}
	@GetMapping("/meter/{meterId}/data")
	public List<ReadingDTO> getMeterData(
	        @PathVariable Long meterId,
	        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
	        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

	    return service.getData(meterId, startDate, endDate);
	}
}
