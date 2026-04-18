package com.swastika_company.PowerReading;
/*
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
*/
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
import com.swastika_company.PowerReading.dto.ReadingByIdDTO;
import com.swastika_company.PowerReading.dto.ReadingEntity;
import com.swastika_company.PowerReading.dto.SimpleMeterDTO;
import com.swastika_company.PowerReading.dto.UserDTO;
import com.swastika_company.PowerReading.entity.Meter;
import com.swastika_company.PowerReading.entity.MeterReading;
import com.swastika_company.PowerReading.entity.User;
import com.swastika_company.PowerReading.repository.MeterRepo;
import com.swastika_company.PowerReading.repository.UserRepo;
import com.swastika_company.PowerReading.service.MeterService;
import com.swastika_company.PowerReading.service.UserService;
*/


@SpringBootApplication
public class PowerReadingApplication{
	/*
  @Autowired
  protected UserRepo ser;
  @Autowired
  protected MeterRepo mser;
  @Autowired
  protected MeterService meterSer;
  
  */
	public static void main(String[] args) {
		SpringApplication.run(PowerReadingApplication.class, args);
		

	}
	/*
	
	@Override
    public void run(String... args) throws Exception {
        // Your logic here
        List<Meter> meters = new ArrayList<>();
        List<MeterReading> readings = new ArrayList<>();
        User user = new User();
        user.setUserName("yash wardhan");
        user.setuserPassword("123456789");

        Meter meter = new Meter();
        meter.setMeterName("yash meter");
        meter.setMacId("iso-78900");
        meter.setMeterNo("123-iso-789");

        MeterReading reading = new MeterReading();
        reading.setDate(LocalDate.now());
        reading.setTime(LocalTime.now());
        reading.setKwh(1001);
        reading.setPf(1.0f);
        readings.add(reading);

        reading.setMeter(meter);
        meters.add(meter);

        meter.setUser(user);
        meter.setMeterReading(readings);
        user.setMeter(meters);

        ser.save(user);

        List<UserDTO> u = ser.getAllUser();
        System.out.println(u);
        
        List<SimpleMeterDTO> m=mser.getUserMeters(1l);
       System.out.println(m);
      
       ReadingEntity r=new ReadingEntity(LocalDate.now(),LocalTime.now(),1002d,1.0f);
       ReadingByIdDTO res=meterSer.addReadings(r, 1l);
       System.out.println(res);
        
      

    }*/
    
}
