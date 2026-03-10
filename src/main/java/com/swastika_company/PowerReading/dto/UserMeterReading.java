package com.swastika_company.PowerReading.dto;

import java.time.LocalDate;
import java.time.LocalTime;

/*
 * date,time,kwh,pf,meter,user
 * */

public record UserMeterReading(LocalDate date,LocalTime time,double kwh,float pf,String meterName,String userName) {
	
	

}
