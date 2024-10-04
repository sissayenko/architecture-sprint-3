/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kz.yandex_practicum.sprint3.profile_management_ms.dto;

/**
 *
 * @author SV
 */
import java.util.List;
import lombok.Data;

@Data
public class HouseDeviceResponse {
    HouseResponse house;
    List<DeviceResponse> devices;
}
