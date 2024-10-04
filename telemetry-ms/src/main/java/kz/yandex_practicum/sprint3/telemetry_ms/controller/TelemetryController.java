package kz.yandex_practicum.sprint3.telemetry_ms.controller;

import java.util.List;
import java.util.Optional;
import java.time.OffsetDateTime;
import kz.yandex_practicum.sprint3.telemetry_ms.dto.TelemetryResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import kz.yandex_practicum.sprint3.telemetry_ms.service.TelemetryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author SV
 */
@RestController
@RequestMapping("/devices")
@RequiredArgsConstructor
public class TelemetryController {

    // private final List<DeviceManagementService> deviceManagementServices;
    private final TelemetryService telemetryService;

    private static final Logger logger = LoggerFactory.getLogger(TelemetryController.class);

    @GetMapping("/{deviceId}/telemetry")
    ResponseEntity<List<TelemetryResponse>> getTelemetry(@PathVariable String deviceId,
            @RequestParam Optional<OffsetDateTime> fromDt, @RequestParam Optional<OffsetDateTime> toDt) {
        logger.info("Get telemetry for device with id: {}", deviceId);
        List<TelemetryResponse> telemetry = telemetryService.getTelemetry(deviceId, fromDt, toDt);
        return ResponseEntity.ok(telemetry);
    }

    @GetMapping("/{deviceId}/telemetry/latest")
    ResponseEntity<List<TelemetryResponse>> getLatestTelemetry(@PathVariable String deviceId) {
        logger.info("Get latest telemetry for device with id: {}", deviceId);
        List<TelemetryResponse> telemetry = telemetryService.getTelemetry(deviceId, null, null);
        if (telemetry.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No telemetry data found for device with id: " + deviceId);
        }
        return ResponseEntity.ok(telemetry);
    }

}
