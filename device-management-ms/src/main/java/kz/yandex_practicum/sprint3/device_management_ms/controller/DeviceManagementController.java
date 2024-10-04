package kz.yandex_practicum.sprint3.device_management_ms.controller;

import java.util.List;
import java.util.Optional;
import kz.yandex_practicum.sprint3.device_management_ms.dto.DeviceRequest;
import kz.yandex_practicum.sprint3.device_management_ms.dto.DeviceResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import kz.yandex_practicum.sprint3.device_management_ms.service.DeviceManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author SV
 */
@RestController
@RequestMapping("/devices")
@RequiredArgsConstructor
public class DeviceManagementController {

    private final List<DeviceManagementService> deviceManagementServices;
        private final DeviceManagementService deviceManagementService;


    private static final Logger logger = LoggerFactory.getLogger(DeviceManagementController.class);

    @PostMapping()
    public ResponseEntity<DeviceResponse> createDevice(@RequestBody DeviceRequest deviceRequest) {

//        Optional<DeviceManagementService> deviceManagementService = deviceManagementServices.stream()
//                .filter(d -> d.HandleDeviceType(deviceRequest.getDeviceType()))
//                .findFirst();

//        DeviceResponse r = null;
//        try {
//            r = deviceManagementService.get().createDevice(deviceRequest.getName(), deviceRequest.getHouseId(), deviceRequest.getDeviceType());
//        } catch (Exception e) {
//            logger.error("Error: No such device type service implementation");
//            throw new ResponseStatusException(
//                    HttpStatus.BAD_REQUEST, "No such device type service implementation"
//            );
//        }

        return ResponseEntity.ok(deviceManagementService.createDevice(deviceRequest.getName(), deviceRequest.getHouseId(), deviceRequest.getDeviceType()));
    }
    
    @GetMapping()
    public ResponseEntity<List<DeviceResponse>> getDeviceList(@RequestParam String houseId) {
        
        return ResponseEntity.ok(deviceManagementService.getDeviceList(houseId));
        
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<DeviceResponse>> getDeviceListAll() {
        
        return ResponseEntity.ok(deviceManagementService.getDeviceListAll());
        
    }
    
    @GetMapping("/{deviceId}")
    public ResponseEntity<DeviceResponse> getDevice(@PathVariable("deviceId") String deviceId) {
        
        
        return ResponseEntity.ok(deviceManagementService.getDevice(deviceId));
    }
    
    @PatchMapping("/{deviceId}/enable")
    public ResponseEntity<DeviceResponse> enableDevice(@PathVariable("deviceId") String deviceId){
        return ResponseEntity.ok(deviceManagementService.enableDevice(deviceId));
    }
    
    @PatchMapping("/{deviceId}/disable")
    public ResponseEntity<DeviceResponse> disableDevice(@PathVariable("deviceId") String deviceId){
        return ResponseEntity.ok(deviceManagementService.disableDevice(deviceId));
    }
}
