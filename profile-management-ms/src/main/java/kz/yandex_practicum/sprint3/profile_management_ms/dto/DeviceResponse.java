package kz.yandex_practicum.sprint3.profile_management_ms.dto;

import lombok.Data;

@Data
public class DeviceResponse {

    private String id;
    private String name;
    private DeviceType deviceType;
    private String houseId;
    private Boolean isEnabled;

}
