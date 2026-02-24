package com.swastika_company.PowerReading.dto;

import java.util.List;



public record UserAndMeter(String userName,List<MeterDTO> meters) {

}
