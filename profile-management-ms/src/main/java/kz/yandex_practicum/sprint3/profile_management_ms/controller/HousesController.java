package kz.yandex_practicum.sprint3.profile_management_ms.controller;

import kz.yandex_practicum.sprint3.profile_management_ms.dto.HouseDeviceResponse;
import kz.yandex_practicum.sprint3.profile_management_ms.dto.HouseResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import kz.yandex_practicum.sprint3.profile_management_ms.service.ProfileManagementService;

/**
 *
 * @author SV
 */
@RestController
@RequestMapping("/houses")
@RequiredArgsConstructor
public class HousesController {

    private final ProfileManagementService profileManagementService;

    private static final Logger logger = LoggerFactory.getLogger(HousesController.class);

    @GetMapping("/{houseId}")
    public ResponseEntity<HouseDeviceResponse>  getHouse(@PathVariable String houseId) {
        return ResponseEntity.ok(profileManagementService.getHouseDetails(houseId));
    }

    @PatchMapping("/{houseId}/enable")
    public ResponseEntity<HouseResponse> enableHouse(@PathVariable String houseId) {
        return ResponseEntity.ok(profileManagementService.enableHouse(houseId));
    }

    @PatchMapping("/{houseId}/disable")
    public ResponseEntity<HouseResponse> disableHouse(@PathVariable String houseId) {
        return ResponseEntity.ok(profileManagementService.disableHouse(houseId));
    }

}
