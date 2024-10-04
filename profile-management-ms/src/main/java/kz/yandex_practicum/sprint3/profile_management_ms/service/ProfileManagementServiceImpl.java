package kz.yandex_practicum.sprint3.profile_management_ms.service;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kz.yandex_practicum.sprint3.profile_management_ms.dto.DeviceResponse;
import kz.yandex_practicum.sprint3.profile_management_ms.dto.HouseResponse;
import kz.yandex_practicum.sprint3.profile_management_ms.dto.HouseholderResponse;
import kz.yandex_practicum.sprint3.profile_management_ms.dto.HouseDeviceResponse;
import kz.yandex_practicum.sprint3.profile_management_ms.mappers.HouseResultRowMapper;
import kz.yandex_practicum.sprint3.profile_management_ms.mappers.HouseholderResultRowMapper;
import kz.yandex_practicum.sprint3.profile_management_ms.mappers.ListHouseResultRowMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.text.RandomStringGenerator;
import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
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
public class ProfileManagementServiceImpl implements ProfileManagementService {

    @Value("${smarthome.device_management.baseUrl}")
    private String deviceBaseUrl = "http://localhost";

    @Autowired
    JdbcTemplate jdbcTemplate;

    // private final List<DeviceType> supportedDeviceTypes = Arrays
    // .asList(DeviceType.CCTV, DeviceType.GATE, DeviceType.LIGHT);
    @Override
    public HouseholderResponse createHouseholder(String name, String email) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate.getDataSource())
                .withSchemaName("profile_management")
                .withTableName("householder")
                .usingColumns("name", "email", "password")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", name);
        parameters.put("email", email);

        RandomStringGenerator g = new RandomStringGenerator.Builder()
                .withinRange('0', 'z')
                .filteredBy(LETTERS, DIGITS)
                .build();
        parameters.put("password", g.generate(20));

        Number id = simpleJdbcInsert.executeAndReturnKey(parameters);

        HouseholderResponse r = this.getHouseholder((Long) id);

        return r;
    }

    // @Override
    // public boolean HandleDeviceType(DeviceType deviceType) {
    // return supportedDeviceTypes.contains(deviceType);
    // }
    // @Override
    // public HouseResponse enableDevice(String deviceId) {
    // final String sql = "update device_management.device set is_enabled = true,
    // update_timestamp = NOW() where id = ?";
    // jdbcTemplate.update(sql, Long.valueOf(deviceId));
    // HouseResponse r = getDevice(deviceId);
    // return r;
    // }
    // @Override
    // public HouseResponse disableDevice(String deviceId) {
    // final String sql = "update device_management.device set is_enabled = false,
    // update_timestamp = NOW() where id = ?";
    // jdbcTemplate.update(sql, Long.valueOf(deviceId));
    // HouseResponse r = getDevice(deviceId);
    // return r;
    // }
    @Override
    public HouseholderResponse getHouseholder(String householderId) {
        return getHouseholder(Long.valueOf(householderId));
    }

    public HouseholderResponse getHouseholder(Long householderId) {
        final String sql = "select id, name, email, is_enabled from profile_management.householder where id = ?";

        HouseholderResponse r = null;
        try {
            r = jdbcTemplate.queryForObject(sql, new HouseholderResultRowMapper(),
                    new SqlParameterValue(Types.BIGINT, householderId));
        } catch (EmptyResultDataAccessException e) {
            System.err.println(e.toString());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such householder");
        }

        return r;
    }

    @Override
    public HouseholderResponse enableHouseholder(String householderId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'enableHouseholder'");
    }

    @Override
    public HouseholderResponse disableHouseholder(String householderId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'disableHouseholder'");
    }

    @Override
    public List<HouseResponse> getHouses(String householderId) {
        return this.getHouses(Long.valueOf(householderId));
    }

    private List<HouseResponse> getHouses(Long householderId) {
        final String sql = "select id, householder_id, address, is_enabled from profile_management.house where householder_id = ?";

        List<HouseResponse> r = null;
        try {
            r = jdbcTemplate.queryForObject(sql, new ListHouseResultRowMapper(),
                    new SqlParameterValue(Types.BIGINT, householderId));
        } catch (EmptyResultDataAccessException e) {
            System.err.println(e.toString());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such householder");
        }

        return r;
    }

    @Override
    public HouseResponse createHouse(String address, String householderId) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate.getDataSource())
                .withSchemaName("profile_management")
                .withTableName("house")
                .usingColumns("householder_id", "address")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("householder_id", Long.valueOf(householderId));
        parameters.put("address", address);

        Number id = simpleJdbcInsert.executeAndReturnKey(parameters);

        HouseResponse r = this.getHouse((Long) id);

        return r;
    }

    private HouseResponse getHouse(Long houseId) {
        final String sql = "select id, householder_id, address, is_enabled from profile_management.house where id = ?";

        HouseResponse r = null;
        try {
            r = jdbcTemplate.queryForObject(sql, new HouseResultRowMapper(),
                    new SqlParameterValue(Types.BIGINT, houseId));
        } catch (EmptyResultDataAccessException e) {
            System.err.println(e.toString());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such householder");
        }

        return r;
    }

    @Override
    public HouseDeviceResponse getHouseDetails(String houseId) {

        HouseDeviceResponse r = new HouseDeviceResponse();

        HouseResponse hr = this.getHouse(Long.valueOf(houseId));

        RestClient restClient = RestClient.builder().baseUrl(this.deviceBaseUrl).build();
        List<DeviceResponse> ldr = restClient.get()
                .uri(uriBuilder -> uriBuilder
                .path("/devices")
                .queryParam("houseId", houseId)
                .build()
                )
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });

        r.setHouse(hr);
        r.setDevices(ldr);

        return r;
    }

    @Override
    public HouseResponse enableHouse(String houseId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'enableHouse'");
    }

    @Override
    public HouseResponse disableHouse(String houseId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'disableHouse'");
    }

}
