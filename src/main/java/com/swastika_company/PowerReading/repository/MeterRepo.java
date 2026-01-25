package com.swastika_company.PowerReading.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swastika_company.PowerReading.entity.Meter;

public interface MeterRepo extends JpaRepository<Meter, Long> {

}
