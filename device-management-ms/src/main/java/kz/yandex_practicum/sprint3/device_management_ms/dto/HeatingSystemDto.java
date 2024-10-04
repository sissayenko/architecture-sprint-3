package kz.yandex_practicum.sprint3.device_management_ms.dto;

import lombok.Data;

@Data
public class HeatingSystemDto {
    private Long id;
    private boolean isOn;
    private double targetTemperature;
    private double currentTemperature;
}