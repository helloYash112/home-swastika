package com.swastika_company.PowerReading.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swastika_company.PowerReading.entity.MeterReading;

public interface MeterReadingRepo extends JpaRepository<MeterReading, Long> {
	
	

}
