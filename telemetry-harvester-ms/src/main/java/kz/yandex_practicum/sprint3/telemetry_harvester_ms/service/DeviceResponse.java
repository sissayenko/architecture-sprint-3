package kz.yandex_practicum.sprint3.telemetry_harvester_ms.service;

import lombok.Data;

@Data
public class DeviceResponse {

    private String id;
    private String name;
    private DeviceType deviceType;
    private String houseId;
    private Boolean isEnabled;

}
