package com.swastika_company.PowerReading;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.swastika_company.PowerReading.service.MeterReadingSer;

@SpringBootApplication
public class PowerReadingApplication {

	public static void main(String[] args) {
		SpringApplication.run(PowerReadingApplication.class, args);
		
		
	}
	
	@Bean
    public CommandLineRunner run(MeterReadingSer service) {
        return args -> {
            System.out.println("Fetching readings...");
            service.getAllMeterReading().forEach(r -> System.out.println(r.toString()));
        };
    }

}
