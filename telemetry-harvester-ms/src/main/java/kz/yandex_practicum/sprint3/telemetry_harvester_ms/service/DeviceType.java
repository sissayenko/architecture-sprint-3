/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kz.yandex_practicum.sprint3.telemetry_harvester_ms.service;

/**
 *
 * @author SV
 */
public enum DeviceType {
    HEATER("heater"),
    CCTV("cctv"),
    GATE("gate"),
    LIGHT("light");
    
    private String value;

    DeviceType(String value) {
      this.value = value;
    }
}
