package com.swastika_company.PowerReading.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swastika_company.PowerReading.repository.MeterReadingRepo;
import com.swastika_company.PowerReading.entity.MeterReading;


@Service
public class MeterReadingSer {
	
	@Autowired
	private MeterReadingRepo repo;
	
	public List<MeterReading> getAllMeterReading(){
		
		return repo.findAll();
		
	}

}
