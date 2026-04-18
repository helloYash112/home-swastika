package com.swastika_company.PowerReading.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.swastika_company.PowerReading.dto.SimpleMeterDTO;
import com.swastika_company.PowerReading.entity.Meter;
import com.swastika_company.PowerReading.entity.MeterReading;

@Repository
public interface MeterRepo extends JpaRepository<Meter, Long> {
	@Query("SELECT m from Meter m where m.meterNumber =:meterNumber")
	public Meter getMeterByNumber(@Param("meterNumber") String meterNumber);
	@Query("SELECT new com.swastika_company.PowerReading.dto.SimpleMeterDTO(m.meterId, m.meterName) " +
		       "FROM Meter m WHERE m.user.id = :userId")
		List<SimpleMeterDTO> getUserMeters(@Param("userId") Long userId);
	
	@Query("""
		    SELECT CASE 
		        WHEN COUNT(m) > 0 THEN true 
		        ELSE false 
		    END
		    FROM Meter m
		    WHERE m.meterNumber = :meterNumber 
		       OR m.machineId = :meterMacAddress
		""")
		boolean existsMeter(
		    @Param("meterNumber") String meterNumber,
		    @Param("meterMacAddress") String meterMacAddress
		);
	
	


}