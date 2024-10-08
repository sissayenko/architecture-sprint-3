package kz.yandex_practicum.sprint3.device_management_ms.service;

import java.util.List;
import kz.yandex_practicum.sprint3.device_management_ms.dto.DeviceResponse;
import kz.yandex_practicum.sprint3.device_management_ms.dto.DeviceType;

/**
 *
 * @author SV
 */
public interface DeviceManagementService {

    public DeviceResponse createDevice(String name, String houseId, DeviceType deviceType);
    
    public List<DeviceResponse> getDeviceListAll();
    
    public List<DeviceResponse> getDeviceList(String houseId);

    public DeviceResponse getDevice(String deviceId);

    public DeviceResponse enableDevice(String deviceId);

    public DeviceResponse disableDevice(String deviceId);

//    public boolean HandleDeviceType(DeviceType deviceType);
}
