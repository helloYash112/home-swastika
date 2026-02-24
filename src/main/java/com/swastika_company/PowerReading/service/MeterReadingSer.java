package com.swastika_company.PowerReading.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.swastika_company.PowerReading.repository.MeterReadingRepo;
import com.swastika_company.PowerReading.dto.MeterAndReading;
import com.swastika_company.PowerReading.entity.MeterReading;


@Service
public class MeterReadingSer {
	
	
	private MeterReadingRepo repo;
	
	public MeterReadingSer(MeterReadingRepo repo) {
		super();
		this.repo = repo;
	}

	public List<MeterReading> getAllMeterReading(){
		
		
		
		return repo.findAll();
		
	}
	public List<MeterAndReading> fetchAllMeterAndReading(){
		return repo.fetchAllMeterAndReading();
		}
	

}
