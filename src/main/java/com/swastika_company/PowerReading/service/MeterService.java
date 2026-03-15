package com.swastika_company.PowerReading.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.swastika_company.PowerReading.dto.CreateMeterDTO;
import com.swastika_company.PowerReading.dto.MeterDTO;
import com.swastika_company.PowerReading.dto.UserAndMeter;
import com.swastika_company.PowerReading.entity.Meter;
import com.swastika_company.PowerReading.entity.User;
import com.swastika_company.PowerReading.repository.MeterRepo;
import com.swastika_company.PowerReading.repository.UserRepo;

import jakarta.transaction.Transactional;

@Service
public class MeterService {
	private MeterRepo service;
	@Autowired
	private UserService userSer;
	@Autowired
	private UserRepo userRepo;

	public MeterService(MeterRepo service) {
		super();
		this.service = service;
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
	   List<MeterDTO>  mdto=service.findAll().stream().map(meter ->new MeterDTO(
			   meter.getMeterName(),meter.getMeterNo(),meter.getMacId())).toList();
	    return mdto;
		
	}
	
    public MeterDTO getMeterById(Long id) {
    	Meter meter=service.getById(id);
    	
    	return new MeterDTO(meter.getMeterName(),meter.getMeterNo(),meter.getMacId());
    }
    
    @Transactional
    public CreateMeterDTO createMeter(CreateMeterDTO meter) {
    	//finding meter have user or not
    	User user=userRepo.findById(meter.userId()).orElseThrow(()-> new RuntimeException("user not found for this meter !"));
    	//getting list of meter
    	List<Meter> meters=new ArrayList<>();
    			meters=user.getMeter();
    	//creating new entity
    	Meter m=new Meter();
    	m.setMacId(meter.meter().meterMacAddress());
    	m.setMeterName(meter.meter().meterName());
    	m.setMeterNo(meter.meter().meterNumber());
    	
    	//adding to list
    	meters.add(m);
    	user.setMeter(meters);
    	Meter rm=service.save(m);
        
    	return new CreateMeterDTO(user.getId(),new MeterDTO(rm.getMeterName(),rm.getMeterNo(),rm.getMacId()));
    }

}
