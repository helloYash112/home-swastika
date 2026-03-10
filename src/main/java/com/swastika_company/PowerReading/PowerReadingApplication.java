package com.swastika_company.PowerReading;

import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
			Optional<User> user=service.findByUserName("yash wardhan");
			System.out.println(user);
		  
		};
    }

}
