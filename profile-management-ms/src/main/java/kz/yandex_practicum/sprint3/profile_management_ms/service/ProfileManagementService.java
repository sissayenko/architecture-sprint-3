package kz.yandex_practicum.sprint3.profile_management_ms.service;

import java.util.List;
import kz.yandex_practicum.sprint3.profile_management_ms.dto.HouseResponse;
import kz.yandex_practicum.sprint3.profile_management_ms.dto.HouseDeviceResponse;
import kz.yandex_practicum.sprint3.profile_management_ms.dto.HouseholderResponse;

/**
 *
 * @author SV
 */
public interface ProfileManagementService {

    public HouseholderResponse createHouseholder(String name, String email);
    
    public HouseholderResponse getHouseholder(String householderId);
    
    public HouseholderResponse enableHouseholder(String householderId);
    
    public HouseholderResponse disableHouseholder(String householderId);
    
    public List<HouseResponse> getHouses(String householderId);
    
    public HouseResponse createHouse(String address, String householderId);
    
    public HouseDeviceResponse getHouseDetails(String houseId);
    
    public HouseResponse enableHouse(String houseId);
    
    public HouseResponse disableHouse(String houseId);
   

    //public boolean HandleDeviceType(DeviceType deviceType);
}
