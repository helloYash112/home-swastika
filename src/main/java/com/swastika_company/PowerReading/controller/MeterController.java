package com.swastika_company.PowerReading.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swastika_company.PowerReading.dto.CreateMeterDTO;
import com.swastika_company.PowerReading.dto.MeterDTO;
import com.swastika_company.PowerReading.service.MeterService;

import jakarta.validation.Valid;

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
	
    @PostMapping
    public ResponseEntity<MeterDTO> createMeter(@Valid @RequestBody CreateMeterDTO request){
    MeterDTO dto = ser.createMeter(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(dto);
}



}
