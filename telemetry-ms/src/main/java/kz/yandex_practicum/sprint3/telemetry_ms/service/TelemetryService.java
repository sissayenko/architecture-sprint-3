package kz.yandex_practicum.sprint3.telemetry_ms.service;

import java.util.List;
import java.util.Optional;
import java.time.OffsetDateTime;
import kz.yandex_practicum.sprint3.telemetry_ms.dto.TelemetryResponse;

/**
 *
 * @author SV
 */
public interface TelemetryService {

    public List<TelemetryResponse> getTelemetry(String deviceId, Optional<OffsetDateTime> fromDt, Optional<OffsetDateTime> toDt);
    //public boolean HandleDeviceType(DeviceType deviceType);
}
