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
import com.swastika_company.PowerReading.dto.ReadingDTO;
import com.swastika_company.PowerReading.dto.ReadingEntity;
import com.swastika_company.PowerReading.dto.SimpleMeterDTO;
import com.swastika_company.PowerReading.dto.UserDTO;
import com.swastika_company.PowerReading.entity.Meter;
import com.swastika_company.PowerReading.entity.MeterReading;
import com.swastika_company.PowerReading.entity.User;
import com.swastika_company.PowerReading.repository.MeterRepo;
import com.swastika_company.PowerReading.repository.UserRepo;
import com.swastika_company.PowerReading.service.MeterReadingSer;
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
  @Autowired
  protected MeterReadingSer mrs;
  */
  
	public static void main(String[] args) {
		SpringApplication.run(PowerReadingApplication.class, args);
		System.out.println("running sucessfully...");

	}
	/*
	
	@Override
    public void run(String... args) throws Exception {
        // Your logic here
		List<Meter> meters = new ArrayList<>();

		User user = new User();
		user.setUserName("rajputyashwardhan17747@gmail.com");
		user.setuserPassword("12345");

		// ----------- METER 1 -----------
		Meter meter1 = new Meter();
		meter1.setMeterName("Meter 1");
		meter1.setMacId("mac-001");
		meter1.setMeterNo("MTR-001");

		List<MeterReading> readings1 = new ArrayList<>();

		// 🔵 MARCH DATA
		for (int i = 1; i <= 3; i++) {
		    MeterReading r = new MeterReading();
		    r.setDate(LocalDate.of(2026, 3, i));
		    r.setTime(LocalTime.now());
		    r.setKwh(900 + i);
		    r.setPf(1.0f);
		    r.setMeter(meter1);
		    readings1.add(r);
		}

		// 🟢 APRIL DATA (current month)
		for (int i = 1; i <= 5; i++) {
		    MeterReading r = new MeterReading();
		    r.setDate(LocalDate.of(2026, 4, i));
		    r.setTime(LocalTime.now());
		    r.setKwh(1000 + i);
		    r.setPf(1.0f);
		    r.setMeter(meter1);
		    readings1.add(r);
		}

		// 🟠 MAY DATA
		for (int i = 1; i <= 3; i++) {
		    MeterReading r = new MeterReading();
		    r.setDate(LocalDate.of(2026, 5, i));
		    r.setTime(LocalTime.now());
		    r.setKwh(1100 + i);
		    r.setPf(0.98f);
		    r.setMeter(meter1);
		    readings1.add(r);
		}

		meter1.setMeterReading(readings1);
		meter1.setUser(user);


		// ----------- METER 2 (optional same pattern) -----------
		Meter meter2 = new Meter();
		meter2.setMeterName("Meter 2");
		meter2.setMacId("mac-002");
		meter2.setMeterNo("MTR-002");

		List<MeterReading> readings2 = new ArrayList<>();

		for (int i = 1; i <= 3; i++) {
		    MeterReading r = new MeterReading();
		    r.setDate(LocalDate.of(2026, 4, i));
		    r.setTime(LocalTime.now());
		    r.setKwh(2000 + i);
		    r.setPf(0.97f);
		    r.setMeter(meter2);
		    readings2.add(r);
		}

		meter2.setMeterReading(readings2);
		meter2.setUser(user);


		// ----------- LINK -----------
		meters.add(meter1);
		meters.add(meter2);
		user.setMeter(meters);

		ser.save(user);

        List<UserDTO> u = ser.getAllUser();
        System.out.println("all user :"+u);
        
        List<SimpleMeterDTO> m=mser.getUserMeters(1l);
       System.out.println("meter user have :"+m);
      
      // ReadingEntity r=new ReadingEntity(LocalDate.now(),LocalTime.now(),1002d,1.0f);
      // ReadingByIdDTO res=meterSer.addReadings(r, 1l);
       //System.out.println(res);
        

       Long meterId = 1L;

       // 🔹 Test 1: Default (Current Month)
       System.out.println("===== CURRENT MONTH DATA =====");
       List<ReadingDTO> current = mrs.getData(meterId, null, null);
       current.forEach(data ->
               System.out.println("Current -> " + data)
       );

       // 🔹 Test 2: March Data
       System.out.println("\n===== MARCH DATA =====");
       List<ReadingDTO> march = mrs.getData(
               meterId,
               LocalDate.of(2026, 3, 1),
               LocalDate.of(2026, 4, 1)
       );
       march.forEach(data ->
               System.out.println("March -> " + data)
       );

       // 🔹 Test 3: April Data
       System.out.println("\n===== APRIL DATA =====");
       List<ReadingDTO> april = mrs.getData(
               meterId,
               LocalDate.of(2026, 4, 1),
               LocalDate.of(2026, 5, 1)
       );
       april.forEach(data ->
               System.out.println("April -> " + data)
       );

       // 🔹 Test 4: May Data
       System.out.println("\n===== MAY DATA =====");
       List<ReadingDTO> may = mrs.getData(
               meterId,
               LocalDate.of(2026, 5, 1),
               LocalDate.of(2026, 6, 1)
       );
       may.forEach(data ->
               System.out.println("May -> " + data)
       );
   }
   */ 
    
}
