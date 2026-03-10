package com.swastika_company.PowerReading.dto;

import java.util.List;

public record UserMeterDTO(String userName,String userPassword,List <MeterDTO> meter) {

}
