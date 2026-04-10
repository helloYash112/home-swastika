package com.swastika_company.PowerReading.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.swastika_company.PowerReading.dto.CreateMeterDTO;
import com.swastika_company.PowerReading.dto.CreateMeterDTO1;
import com.swastika_company.PowerReading.dto.MeterDTO;
import com.swastika_company.PowerReading.dto.ReadingByIdDTO;
import com.swastika_company.PowerReading.dto.ReadingDTO;
import com.swastika_company.PowerReading.dto.ReadingEntity;
import com.swastika_company.PowerReading.dto.SimpleMeterDTO;
import com.swastika_company.PowerReading.entity.Meter;

import com.swastika_company.PowerReading.entity.MeterReading;

import com.swastika_company.PowerReading.entity.User;
import com.swastika_company.PowerReading.repository.MeterReadingRepo;
import com.swastika_company.PowerReading.repository.MeterRepo;
import com.swastika_company.PowerReading.repository.UserRepo;


import jakarta.persistence.EntityNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class MeterService {


    @Autowired
	private MeterRepo meterRepo;

	@Autowired
	private UserRepo userRepo;
	@Autowired 
	private MeterReadingRepo meterReadingRepo;

	public MeterService(MeterRepo meterRepo) {
		super();
		this.meterRepo= meterRepo;
	}
	
	/*
GET /api/meters → list all meters /done

GET /api/meters/{id} → get one meter /done

POST /api/meters → create meter /done

PUT /api/meters/{id} → update meter/done

DELETE /api/meters/{id} → delete meter/done

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
		//checking a meter is all ready declared or not ?
		
		
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
    
    
    public MeterDTO updateMeter(Long id,MeterDTO request) {
    	//check meter have or not update
    	Meter meter=meterRepo.findById(id).orElseThrow(()-> new EntityNotFoundException("meter not found id: "+id));
    	//updating the meter with data 
    	meter.setMacId(request.meterMacAddress());
    	meter.setMeterName(request.meterName());
    	meter.setMeterNo(request.meterNumber());
        //saving the data
    	Meter mres=meterRepo.save(meter);
    	
    	return new  MeterDTO(mres.getMeterName(),mres.getMeterNo(),mres.getMacId());
    }
    
    public void deleteMeterById(Long id) {
    	meterRepo.deleteById(id);
    
    	return;
    }
    
    /*
     * public record ReadingDTO(LocalDate date,LocalTime time,double kwh,float pf) {
} */
    
    @Transactional
    public ReadingByIdDTO addReadings(ReadingEntity reqReading, Long id) {

        Meter meter = meterRepo.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("meter record not found id: " + id));

        if (reqReading == null) {
            throw new IllegalArgumentException("Reading cannot be null");
        }

        MeterReading r = new MeterReading();
        r.setDate(reqReading.date());
        r.setKwh(reqReading.kwh());
        r.setPf(reqReading.pf());
        r.setTime(reqReading.time());

        // 🔥 MOST IMPORTANT LINE
        r.setMeter(meter);

        MeterReading savedReading = meterReadingRepo.save(r);

        ReadingDTO resDto = new ReadingDTO(
            savedReading.getId(),
            savedReading.getDate(),
            savedReading.getTime(),
            savedReading.getKwh(),
            savedReading.getPf()
        );

        return new ReadingByIdDTO(meter.getMeterId(), List.of(resDto));
    }
    // getting list of meters by user id
    public List<SimpleMeterDTO> getByUserId(Long id) {
        // check if user exists
        if (userRepo.findById(id).isEmpty()) {
            throw new EntityNotFoundException("User not found for given Id: " + id);
        }
        return meterRepo.getUserMeters(id);
    }
    @Transactional
	public SimpleMeterDTO createMeter(CreateMeterDTO1 request) {
		// TODO Auto-generated method stub
    	  if (request == null || request.meter() == null) {
    	        throw new IllegalArgumentException("Meter data is required");
    	    }
    	  //getting user by id
    	  User user = userRepo.findById(request.userId())
    		        .orElseThrow(() -> new EntityNotFoundException(
    		            "User not found with id: " + request.userId()));
    	  //checking meter exit or not
    	  boolean exist=meterRepo.existsMeter(request.meter().meterNumber(), request.meter().meterMacAddress());
    	  if(exist) {
    		  throw new IllegalArgumentException(
    		            "Meter already exists with same number or MAC address");
    	  }
    	  Meter meter = new Meter();
    	    meter.setMeterName(request.meter().meterName());
    	    meter.setMeterNo(request.meter().meterNumber());
    	    meter.setMacId(request.meter().meterMacAddress());
    	    
    	    meter.setUser(user);
    	    Meter saved = meterRepo.save(meter);
    	    return new SimpleMeterDTO(
    	            saved.getMeterId(),
    	            saved.getMeterName()
    	        );
	}


}
