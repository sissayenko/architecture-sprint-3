package kz.yandex_practicum.sprint3.device_management_ms.dto;

import kz.yandex_practicum.sprint3.device_management_ms.dto.DeviceType;
import lombok.Data;

@Data
public class DeviceResponse {

    private String id;
    private String name;
    private DeviceType deviceType;
    private String houseId;
    private String externalId;
    private Boolean isEnabled;

}
