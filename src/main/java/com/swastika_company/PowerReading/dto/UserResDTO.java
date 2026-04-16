package com.swastika_company.PowerReading.dto;

import java.util.List;

import java.util.List;

public record UserResDTO(Long userId, String userName, List<MeterResDTO> meters) {

    // Primary constructor ensures meters is never null
    public UserResDTO {
        meters = meters != null ? meters : List.of();
    }

    // Convenience constructor: user without meters
    public UserResDTO(Long userId, String userName) {
        this(userId, userName, List.of());
    }
}
