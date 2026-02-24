package com.swastika_company.PowerReading.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swastika_company.PowerReading.entity.MeterReading;
import com.swastika_company.PowerReading.dto.MeterAndReading;

@Repository

public interface MeterReadingRepo extends JpaRepository<MeterReading, Long> {
	
	List<MeterAndReading> fetchAllMeterAndReading();

}
