package com.swastika_company.PowerReading.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.swastika_company.PowerReading.entity.Meter;
import com.swastika_company.PowerReading.entity.MeterReading;
import com.swastika_company.PowerReading.dto.MeterAndReading;
import com.swastika_company.PowerReading.dto.UserMeterReading;

@Repository

public interface MeterReadingRepo extends JpaRepository<MeterReading, Long> {
	
	List<MeterAndReading> fetchAllMeterAndReading();
	/*
	 * public record MeterReading(LocalDate date,LocalTime time,double kwh,float pf,String meterName,String userName) 
*/
	
	@Query("SELECT new com.swastika_company.PowerReading.dto.UserMeterReading(r.date, r.time, r.kwh, r.pf, m.meterName, u.userName) " +
		       "FROM MeterReading r " +
		       "JOIN r.meter m " +
		       "JOIN m.user u " +
		       "WHERE u.id = :id")
		List<UserMeterReading> getAllReadingByID(@Param("id") Long id);
	Optional<MeterReading> findTopByMeterOrderByDateDescTimeDescIdDesc(Meter meter);
	@Query("SELECT r FROM MeterReading  r " +
	           "WHERE r.meter.id = :meterId " +
		       "AND r.date >= :startDate " +
		       "AND r.date < :endDate")
		List<MeterReading> findCurrentMonthData(
				@Param("meterId") Long meterId,
		        @Param("startDate") LocalDate startDate,
		        @Param("endDate") LocalDate endDate);
	
	

}
