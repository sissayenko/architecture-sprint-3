package kz.yandex_practicum.sprint3.profile_management_ms.dto;


/**
 * DeviceRequest
 */

import lombok.Data;

@Data
public class DeviceRequest   {
    private String name;
    private DeviceType deviceType;  
    private String houseId;
  
}
