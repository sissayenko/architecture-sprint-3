/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kz.yandex_practicum.sprint3.telemetry_ms.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import kz.yandex_practicum.sprint3.telemetry_ms.dto.TelemetryResponse;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author SV
 */
public class TelemetryResponseRowMapper implements RowMapper<List<TelemetryResponse>> {

    @Override
    public List<TelemetryResponse> mapRow(ResultSet rs, int rowNum) throws SQLException {
        List<TelemetryResponse> telemetryResponses = new ArrayList<>();

        do {
            TelemetryResponse telemetryResponse = new TelemetryResponse();
            telemetryResponse.setId(rs.getString("id"));
            telemetryResponse.setDeviceId(rs.getString("device_id"));
            telemetryResponse.setTimestamp(rs.getTimestamp("telemetry_timestamp").toInstant().atOffset(ZoneOffset.UTC));
            telemetryResponse.setSensorName(rs.getString("sensor"));
            telemetryResponse.setUnit(rs.getString("unit"));
            telemetryResponse.setValue(rs.getString("value"));
            
            telemetryResponses.add(telemetryResponse);
        } while (rs.next());

        return telemetryResponses;
    }
}
