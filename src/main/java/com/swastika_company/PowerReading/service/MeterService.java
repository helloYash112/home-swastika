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
import com.swastika_company.PowerReading.dto.MeterDTO;
import com.swastika_company.PowerReading.dto.ReadingDTO;
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
    public List<ReadingDTO> addReadings(List<ReadingDTO> reqReading,Long id){
    	
    	List<MeterReading> reading=new ArrayList<>();
    	//getting specific meter or throw exception
    	Meter meter=meterRepo.findById(id).orElseThrow(()-> new EntityNotFoundException("meter record not found id: "+id));
    	//checking is reading is null or not 
    	if(reqReading != null) {
    		for(ReadingDTO dto : reqReading) {
    			//saving and responding
    			MeterReading r=new MeterReading();
    			r.setDate(dto.date());
    			r.setKwh(dto.kwh());
    			r.setPf(dto.pf());
    			r.setTime(dto.time());
    			r.setMeter(meter);
    			reading.add(r);
    		}
    		
    	}
    	//reading adding to meter
    	meter.setMeterReading(reading);
    	
    	Meter res=meterRepo.save(meter);
    	//returning back to saved data
    	List<ReadingDTO> resDto=res.getMeterReading().stream().map(r-> new ReadingDTO(r.getDate(),r.getTime(),
    			r.getKwh(),r.getPf())).toList();
    	return resDto;
    	
    }
    
    

}
