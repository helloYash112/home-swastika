package com.swastika_company.PowerReading.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swastika_company.PowerReading.dto.CreateMeterDTO;
import com.swastika_company.PowerReading.dto.CreateMeterDTO1;
import com.swastika_company.PowerReading.dto.MeterDTO;
import com.swastika_company.PowerReading.dto.ReadingByIdDTO;
import com.swastika_company.PowerReading.dto.ReadingDTO;
import com.swastika_company.PowerReading.dto.ReadingEntity;
import com.swastika_company.PowerReading.dto.SimpleMeterDTO;
import com.swastika_company.PowerReading.service.MeterService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/meters")
public class MeterController {
	private MeterService ser;

	public MeterController(MeterService ser) {
		super();
		this.ser = ser;
	}

	@GetMapping
	public List<MeterDTO> getAllMeter() {
		return ser.getMeters();

	}

	@GetMapping("/{id}")
	public MeterDTO getMeterById(@PathVariable("id") Long id) {
		return ser.getMeterById(id);

	}
	//getting meters by user id
	@GetMapping("/user/{id}")
	public ResponseEntity<List<SimpleMeterDTO>> getByUserId(@PathVariable Long id) {
	    List<SimpleMeterDTO> meters = ser.getByUserId(id);
	    return ResponseEntity.ok(meters);
	}


	@PostMapping
	public ResponseEntity<MeterDTO> createMeter(@Valid @RequestBody CreateMeterDTO request) {
		MeterDTO dto = ser.createMeter(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<MeterDTO> updateMeter(@PathVariable Long id, @RequestBody MeterDTO requset) {
		MeterDTO dto = ser.updateMeter(id, requset);
		return ResponseEntity.ok(dto);
	}

	@DeleteMapping("/{id}")
	public void deleteMeter(@PathVariable Long id) {
		ser.deleteMeterById(id);
		return;
	}

	@PostMapping("/reading/{id}")

	public ResponseEntity<ReadingByIdDTO> setReadingById(@PathVariable Long id,
			@RequestBody ReadingEntity readings) {

	
		ReadingByIdDTO dto = ser.addReadings(readings, id);
		return ResponseEntity.ok(dto);

	}
	//creating meter method 2
	@PostMapping("/meter")
	public ResponseEntity<SimpleMeterDTO> createMeter(
	        @RequestBody CreateMeterDTO1 request) {

	    SimpleMeterDTO response = ser.createMeter(request);
	    return ResponseEntity.ok(response);
	}

}
