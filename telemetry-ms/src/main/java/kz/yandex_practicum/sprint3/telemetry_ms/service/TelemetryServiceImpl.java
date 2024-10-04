package kz.yandex_practicum.sprint3.telemetry_ms.service;

import java.sql.Types;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.time.OffsetDateTime;
import kz.yandex_practicum.sprint3.telemetry_ms.dto.TelemetryResponse;
import kz.yandex_practicum.sprint3.telemetry_ms.mappers.TelemetryResponseRowMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author SV
 */
@Service
@RequiredArgsConstructor
public class TelemetryServiceImpl implements TelemetryService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<TelemetryResponse> getTelemetry(String deviceId, Optional<OffsetDateTime> fromDt, Optional<OffsetDateTime> toDt) {
        final String sql = "select id, device_id, telemetry_timestamp, value, unit, sensor from telemetry.telemetry where device_id = ? order by id desc limit 100";

        List<TelemetryResponse> r = null;

        try {
            r = jdbcTemplate.queryForObject(sql, new TelemetryResponseRowMapper(), new SqlParameterValue(Types.VARCHAR, deviceId));
        } catch (EmptyResultDataAccessException e) {
            System.err.println(e.toString());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such device type service implementation");
        }
        return r;

    }

}
