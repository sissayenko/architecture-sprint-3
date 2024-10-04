package kz.yandex_practicum.sprint3.telemetry_ms.dto;

import lombok.Data;
import java.time.OffsetDateTime;

@Data
public class TelemetryResponse {
    private String id;
    private String deviceId;
    private OffsetDateTime timestamp;
    private String sensorName;
    private String unit;
    private String value;
}
