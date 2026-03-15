package com.swastika_company.PowerReading.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.swastika_company.PowerReading.entity.Meter;

@Repository
public interface MeterRepo extends JpaRepository<Meter, Long> {

}