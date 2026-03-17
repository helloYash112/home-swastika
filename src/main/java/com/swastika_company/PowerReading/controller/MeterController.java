package com.swastika_company.PowerReading.controller;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
import com.swastika_company.PowerReading.dto.MeterDTO;
import com.swastika_company.PowerReading.dto.ReadingDTO;
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
    @PutMapping("/{id}")
    public ResponseEntity<MeterDTO> updateMeter(@PathVariable Long id,@RequestBody MeterDTO requset) {
    	MeterDTO dto=ser.updateMeter(id, requset);
    	return ResponseEntity.ok(dto);
    }
    @DeleteMapping("/{id}")
    public void deleteMeter(@PathVariable Long id) {
    	ser.deleteMeterById(id);
    	return;
    }
    @PostMapping("/reading/{id}")
    public ResponseEntity<List<ReadingDTO>> setReadingById(@PathVariable Long id,@RequestBody List<ReadingDTO> readings){
    	List<ReadingDTO> dto=ser.addReadings(readings, id);
    	return ResponseEntity.ok(dto);
    	
    }
    


}
