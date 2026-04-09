package com.swastika_company.PowerReading.dto;

import java.util.List;

public record ReadingByIdDTO(Long id,List<ReadingDTO> readings) {

}
