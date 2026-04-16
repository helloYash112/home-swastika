package com.swastika_company.PowerReading.dto;

import java.util.List;

public record MeterResDTO(Long id, String name, List<ReadingDTO> readings) {
    public MeterResDTO {
        // ensure readings is never null
        readings = readings != null ? readings : List.of();
    }
}
