package kz.yandex_practicum.sprint3.profile_management_ms.controller;

import java.util.List;
import kz.yandex_practicum.sprint3.profile_management_ms.dto.HouseRequest;
import kz.yandex_practicum.sprint3.profile_management_ms.dto.HouseResponse;
import kz.yandex_practicum.sprint3.profile_management_ms.dto.HouseholderRequest;
import kz.yandex_practicum.sprint3.profile_management_ms.dto.HouseholderResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import kz.yandex_practicum.sprint3.profile_management_ms.service.ProfileManagementService;

/**
 *
 * @author SV
 */
@RestController
@RequestMapping("/householders")
@RequiredArgsConstructor
public class HouseholdersController {

    private final ProfileManagementService profileManagementService;

    private static final Logger logger = LoggerFactory.getLogger(HouseholdersController.class);

    @PostMapping()
    public ResponseEntity<HouseholderResponse> createHouseholder(@RequestBody HouseholderRequest householderRequest) {
        return ResponseEntity.ok(profileManagementService.createHouseholder(householderRequest.getName(), householderRequest.getEmail()));
    }

    @GetMapping("/{householderId}")
    public ResponseEntity<HouseholderResponse>  getHouseholder(@PathVariable String householderId) {
        return ResponseEntity.ok(profileManagementService.getHouseholder(householderId));
    }
    
    @GetMapping("/{householderId}/houses")
    public ResponseEntity<List<HouseResponse>> getHouses(@PathVariable String householderId) {
        return ResponseEntity.ok(profileManagementService.getHouses(householderId));
    }

    @PostMapping("/{householderId}/houses")
    public ResponseEntity<HouseResponse> createHouse(@PathVariable String householderId, @RequestBody HouseRequest houseRequest) {
        return ResponseEntity.ok(profileManagementService.createHouse(houseRequest.getAddress(), householderId));
    }
    
    
}
