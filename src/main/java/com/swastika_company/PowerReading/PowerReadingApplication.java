package com.swastika_company.PowerReading;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.swastika_company.PowerReading.entity.Meter;
import com.swastika_company.PowerReading.entity.MeterReading;
import com.swastika_company.PowerReading.entity.User;
import com.swastika_company.PowerReading.repository.UserRepo;


@SpringBootApplication
public class PowerReadingApplication {

	public static void main(String[] args) {
		SpringApplication.run(PowerReadingApplication.class, args);
		
		
	}
	
	@Bean
    public CommandLineRunner run(UserRepo service) {
		return args -> {
			//creating user
			User user=new User();
			user.setUserName("yash wardhan");
			user.setuserPassword("7889456123");
			//creating meter
			Meter m=new Meter();
			m.setMacId("456-789-123");
			m.setMeterName("swastika home");
			m.setMeterNo("789-852-741");
			//creating reading
			MeterReading reading=new MeterReading();
			reading.setDate(LocalDate.now());
			reading.setTime(LocalTime.now());
			reading.setKwh(1005);
			reading.setPf(1);
			reading.setMeter(m);
			//adding to each other
			List<Meter> meters=new ArrayList<>();
			List<MeterReading> readings=new ArrayList<>();
			readings.add(reading);
			
			meters.add(m);
			user.setMeter(meters);
			m.setMeterReading(readings);
			m.setUser(user);
			m.setMeterReading(readings);
			service.save(user);
			System.out.println("created sucessfully...");
			
		};
    }

}
