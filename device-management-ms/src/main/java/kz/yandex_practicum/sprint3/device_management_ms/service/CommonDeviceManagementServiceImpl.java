package kz.yandex_practicum.sprint3.device_management_ms.service;

import java.sql.Types;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kz.yandex_practicum.sprint3.device_management_ms.dto.DeviceResponse;
import kz.yandex_practicum.sprint3.device_management_ms.dto.DeviceType;
import kz.yandex_practicum.sprint3.device_management_ms.dto.HeatingSystemDto;
import kz.yandex_practicum.sprint3.device_management_ms.mappers.DeviceResultRowMapper;
import kz.yandex_practicum.sprint3.device_management_ms.mappers.ListDeviceResultRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author SV
 */
@Service
@RequiredArgsConstructor
public class CommonDeviceManagementServiceImpl implements DeviceManagementService {

    @Value("${smarthome.monolith.baseUrl}")
    private String monolithBaseUrl = "http://localhost";

    @Autowired
    JdbcTemplate jdbcTemplate;

//    private final List<DeviceType> supportedDeviceTypes = Arrays
//            .asList(DeviceType.CCTV, DeviceType.GATE, DeviceType.LIGHT);
    @Override
    public DeviceResponse createDevice(String name, String houseId, DeviceType deviceType) {

        String externalId = null;

        if (deviceType.equals(DeviceType.HEATER)) {

            HeatingSystemDto hs = new HeatingSystemDto();

            hs.setCurrentTemperature(0);
            hs.setTargetTemperature(0);

            RestClient restClient = RestClient.builder().baseUrl(this.monolithBaseUrl).build();
            HeatingSystemDto hsdr = restClient.post()
                    .uri(uriBuilder -> uriBuilder
                    .path("/api/heating")
                    .build()
                    )
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(hs)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });

            externalId = hsdr.getId().toString();
        }

        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate.getDataSource())
                .withSchemaName("device_management")
                .withTableName("device")
                .usingColumns("name", "house_id", "r_device_type_code", "external_id")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", name);
        parameters.put("house_id", houseId);
        parameters.put("r_device_type_code", deviceType.toString());
        parameters.put("external_id", externalId);

        Number id = simpleJdbcInsert.executeAndReturnKey(parameters);

        DeviceResponse r = this.getDevice((Long) id);

        return r;
    }

//    @Override
//    public boolean HandleDeviceType(DeviceType deviceType) {
//        return supportedDeviceTypes.contains(deviceType);
//    }
    @Override
    public DeviceResponse getDevice(String deviceId) {

        return this.getDevice(Long.valueOf(deviceId));
    }

    private DeviceResponse getDevice(Long deviceId) {

        final String sql = "select id, house_id, r_device_type_code, name, is_enabled, external_id from device_management.device where id = ?";

        DeviceResponse r = null;
        try {
            r = jdbcTemplate.queryForObject(sql, new DeviceResultRowMapper(), new SqlParameterValue(Types.BIGINT, deviceId));
        } catch (EmptyResultDataAccessException e) {
            System.err.println(e.toString());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such device type service implementation");
        }
        return r;
    }

    @Override
    public DeviceResponse enableDevice(String deviceId) {
        final String sql = "update device_management.device set is_enabled = true, update_timestamp = NOW() where id = ?";

        DeviceResponse dr = this.getDevice(deviceId);

        if (dr.getDeviceType().equals(DeviceType.HEATER)) {

            RestClient restClient = RestClient.builder().baseUrl(this.monolithBaseUrl).build();
            HeatingSystemDto hsdr = restClient.post()
                    .uri(uriBuilder -> uriBuilder
                    .path("/api/heating/{id}/turn-on")
                    .build(dr.getExternalId())
                    )
                    .contentType(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
        }

        jdbcTemplate.update(sql, Long.valueOf(deviceId));

        return getDevice(deviceId);
    }

    @Override
    public DeviceResponse disableDevice(String deviceId) {
        final String sql = "update device_management.device set is_enabled = false, update_timestamp = NOW() where id = ?";
        
        DeviceResponse dr = this.getDevice(deviceId);

        if (dr.getDeviceType().equals(DeviceType.HEATER)) {

            RestClient restClient = RestClient.builder().baseUrl(this.monolithBaseUrl).build();
            HeatingSystemDto hsdr = restClient.post()
                    .uri(uriBuilder -> uriBuilder
                    .path("/api/heating/{id}/turn-off")
                    .build(dr.getExternalId())
                    )
                    .contentType(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
        }

        jdbcTemplate.update(sql, Long.valueOf(deviceId));

        return getDevice(deviceId);
    }

    @Override
    public List<DeviceResponse> getDeviceList(String houseId) {
        final String sql = "select id, house_id, r_device_type_code, name, is_enabled from device_management.device where house_id = ?";

        List<DeviceResponse> r = null;
        try {
            r = jdbcTemplate.queryForObject(sql, new ListDeviceResultRowMapper(), new SqlParameterValue(Types.VARCHAR, houseId));
        } catch (EmptyResultDataAccessException e) {
            System.err.println(e.toString());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such device type service implementation");
        }
        return r;
    }
    
    @Override
    public List<DeviceResponse> getDeviceListAll() {
        final String sql = "select id, house_id, r_device_type_code, name, is_enabled from device_management.device where is_enabled = true";

        List<DeviceResponse> r = null;
        try {
            r = jdbcTemplate.queryForObject(sql, new ListDeviceResultRowMapper());
        } catch (EmptyResultDataAccessException e) {
            System.err.println(e.toString());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such device type service implementation");
        }
        return r;
    }

}
