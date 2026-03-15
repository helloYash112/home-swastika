package com.swastika_company.PowerReading.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.swastika_company.PowerReading.dto.CreateMeterDTO;
import com.swastika_company.PowerReading.dto.MeterDTO;

import com.swastika_company.PowerReading.entity.Meter;

import com.swastika_company.PowerReading.entity.MeterReading;

import com.swastika_company.PowerReading.entity.User;
import com.swastika_company.PowerReading.repository.MeterRepo;
import com.swastika_company.PowerReading.repository.UserRepo;


import jakarta.persistence.EntityNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class MeterService {



	private MeterRepo meterRepo;

	@Autowired
	private UserRepo userRepo;

	public MeterService(MeterRepo meterRepo) {
		super();
		this.meterRepo= meterRepo;
	}
	
	/*
GET /api/meters → list all meters /done

GET /api/meters/{id} → get one meter /done

POST /api/meters → create meter

PUT /api/meters/{id} → update meter

DELETE /api/meters/{id} → delete meter

POST /api/meters/{meterId}/readings → add a reading to a specific meter

*/
	
	public List<MeterDTO> getMeters(){
	   List<MeterDTO>  mdto=meterRepo.findAll().stream().map(meter ->new MeterDTO(
			   meter.getMeterName(),meter.getMeterNo(),meter.getMacId())).toList();
	    return mdto;
		
	}
	
    public MeterDTO getMeterById(Long id) {
    	Meter meter=meterRepo.getById(id);
    	
    	return new MeterDTO(meter.getMeterName(),meter.getMeterNo(),meter.getMacId());
    }
    
    @Transactional
    public MeterDTO createMeter(CreateMeterDTO request) {
		//finding exting user is there or not
		Long id=(Long)request.userId();
		User user=userRepo.findById(id).orElseThrow(()-> new EntityNotFoundException("user record no found to this id :"+ id));
		//creating a meter entity
		Meter m=new Meter();
		m.setMacId(request.meter().meterMacAddress());
		m.setMeterName(request.meter().meterName());
		m.setMeterNo(request.meter().meterNumber());
		m.setUser(user);
		//checking reading entity is not null
		if(request.reading() != null){
			List<MeterReading> list=new ArrayList<>();
			MeterReading reading=new MeterReading();
			reading.setDate(request.reading().date());
			reading.setTime(request.reading().time());
			reading.setKwh(request.reading().kwh());
			reading.setPf(request.reading().pf());
			reading.setMeter(m);
			list.add(reading);
			m.setMeterReading(list);
			
		}
		//savinfg data 
		Meter mres=meterRepo.save(m);
		//returning responce
		MeterDTO res=new MeterDTO(mres.getMeterName(),mres.getMeterNo(),mres.getMacId());


    	return res;

    }

}
