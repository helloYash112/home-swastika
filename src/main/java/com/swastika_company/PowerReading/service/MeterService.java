package com.swastika_company.PowerReading.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.swastika_company.PowerReading.dto.MeterDTO;
import com.swastika_company.PowerReading.entity.Meter;
import com.swastika_company.PowerReading.repository.MeterRepo;

@Service
public class MeterService {
	private MeterRepo service;

	public MeterService(MeterRepo service) {
		super();
		this.service = service;
	}
	
	/*
GET /api/meters → list all meters /done

GET /api/meters/{id} → get one meter

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

}
