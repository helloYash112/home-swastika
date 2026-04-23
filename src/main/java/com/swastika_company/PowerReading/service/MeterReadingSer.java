package com.swastika_company.PowerReading.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swastika_company.PowerReading.repository.MeterReadingRepo;
import com.swastika_company.PowerReading.repository.MeterRepo;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import com.swastika_company.PowerReading.dto.MeterAndReading;
import com.swastika_company.PowerReading.dto.ReadingDTO;
import com.swastika_company.PowerReading.dto.ReadingEntity;
import com.swastika_company.PowerReading.dto.UserMeterReading;
import com.swastika_company.PowerReading.entity.Meter;
import com.swastika_company.PowerReading.entity.MeterReading;


@Service
public class MeterReadingSer {
	/*
	 * GET /reading → list all readings/done

       GET /reading/{id} → get one reading/done

       (optional) GET /reading/user/{userId} → list readings by user/done*/
	
	private MeterReadingRepo repo;
	@Autowired
	private MeterRepo mrepo;
	
	public MeterReadingSer(MeterReadingRepo repo) {
		super();
		this.repo = repo;
	}

	@Transactional
	public List<MeterAndReading> fetchAllMeterAndReading(){
		return repo.fetchAllMeterAndReading();
		}
	
	public List<UserMeterReading> getReadingByUserId(Long id){
		return repo.getAllReadingByID(id);
	}
	
	//get reading by meter id
	@Transactional
	public List<ReadingEntity> getById(Long id){
		Meter meter=mrepo.findById(id).orElseThrow(()->new EntityNotFoundException("meter record is not found to this id :"+id));
		System.out.println("meter "+meter);
		List<MeterReading> readings=meter.getMeterReading();
		List<ReadingEntity> res=readings.stream().map(r->new ReadingEntity(r.getDate(),
				r.getTime(),
				r.getKwh(),
				r.getPf())).toList();
		
		return res;
		
	}
	//getting reading by id 
	public ReadingEntity getReadingById(Long id) {
		MeterReading r=repo.getById(id);
		return new ReadingEntity(r.getDate(),r.getTime(),r.getKwh(),r.getPf());
	}
	//delete the reading by id
	public void deleteById(Long id) {
		 repo.deleteById(id);
		 return;
	}
	 public List<ReadingDTO> getData(Long meterId,LocalDate startDate, LocalDate endDate) {
		 // 1. Check if meter exists
		    Meter meter = mrepo.findById(meterId)
		            .orElseThrow(() -> new RuntimeException("Meter not found with id: " + meterId));

		    //  2. Default date handling
		    if (startDate == null && endDate == null) {
		        LocalDate now = LocalDate.now();
		        startDate = now.withDayOfMonth(1);
		        endDate = startDate.plusMonths(1);
		    } 
		    else if (startDate != null && endDate == null) {
		        endDate = startDate.plusMonths(1);
		    }
	        List<MeterReading> readings=repo.findCurrentMonthData(meterId,startDate, endDate);
	        

	        return  readings.stream().map(r->new ReadingDTO(r.getId(),r.getDate(),r.getTime(),r.getKwh(),r.getPf())).toList();
	    }

}
