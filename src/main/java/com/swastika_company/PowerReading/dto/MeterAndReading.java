package com.swastika_company.PowerReading.dto;
import java.time.LocalDate;
import java.time.LocalTime;

public record MeterAndReading(
		LocalDate date,LocalTime time,double kwh,float pf,String macId,String meterName) {

}
