package ru.yandex.practicum.smarthome.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.smarthome.dto.HeatingSystemDto;
import ru.yandex.practicum.smarthome.entity.HeatingSystem;
import ru.yandex.practicum.smarthome.repository.HeatingSystemRepository;

@Service
@RequiredArgsConstructor
public class HeatingSystemServiceImpl implements HeatingSystemService {
    private final HeatingSystemRepository heatingSystemRepository;
    
    @Override
    public HeatingSystemDto createHeatingSystem(HeatingSystemDto heatingSystemDto) {
        HeatingSystem hs = new HeatingSystem();
        hs.setCurrentTemperature(heatingSystemDto.getCurrentTemperature());
        hs.setTargetTemperature(heatingSystemDto.getTargetTemperature());
        hs.setOn(true);
        
        heatingSystemRepository.save(hs);
        
        heatingSystemDto.setId(hs.getId());
        
        return(heatingSystemDto);
        
    }
    
    @Override
    public HeatingSystemDto getHeatingSystem(Long id) {
        HeatingSystem heatingSystem = heatingSystemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("HeatingSystem not found"));
        return convertToDto(heatingSystem);
    }

    @Override
    public HeatingSystemDto updateHeatingSystem(Long id, HeatingSystemDto heatingSystemDto) {
        HeatingSystem existingHeatingSystem = heatingSystemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("HeatingSystem not found"));
        existingHeatingSystem.setOn(heatingSystemDto.isOn());
        existingHeatingSystem.setTargetTemperature(heatingSystemDto.getTargetTemperature());
        existingHeatingSystem.setCurrentTemperature(heatingSystemDto.getCurrentTemperature());
        HeatingSystem updatedHeatingSystem = heatingSystemRepository.save(existingHeatingSystem);
        return convertToDto(updatedHeatingSystem);
    }

    @Override
    public void turnOn(Long id) {
        HeatingSystem heatingSystem = heatingSystemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("HeatingSystem not found"));
        heatingSystem.setOn(true);
        heatingSystemRepository.save(heatingSystem);
    }

    @Override
    public void turnOff(Long id) {
        HeatingSystem heatingSystem = heatingSystemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("HeatingSystem not found"));
        heatingSystem.setOn(false);
        heatingSystemRepository.save(heatingSystem);
    }

    @Override
    public void setTargetTemperature(Long id, double temperature) {
        HeatingSystem heatingSystem = heatingSystemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("HeatingSystem not found"));
        heatingSystem.setTargetTemperature(temperature);
        heatingSystemRepository.save(heatingSystem);
    }

    @Override
    public Double getCurrentTemperature(Long id) {
        HeatingSystem heatingSystem = heatingSystemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("HeatingSystem not found"));
        return heatingSystem.getCurrentTemperature();
    }

    private HeatingSystemDto convertToDto(HeatingSystem heatingSystem) {
        HeatingSystemDto dto = new HeatingSystemDto();
        dto.setId(heatingSystem.getId());
        dto.setOn(heatingSystem.isOn());
        dto.setTargetTemperature(heatingSystem.getTargetTemperature());
        dto.setCurrentTemperature(heatingSystem.getCurrentTemperature());
        return dto;
    }
}