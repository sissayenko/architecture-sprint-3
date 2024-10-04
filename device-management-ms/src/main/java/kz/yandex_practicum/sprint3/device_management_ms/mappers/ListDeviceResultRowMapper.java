/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kz.yandex_practicum.sprint3.device_management_ms.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import kz.yandex_practicum.sprint3.device_management_ms.dto.DeviceResponse;
import kz.yandex_practicum.sprint3.device_management_ms.dto.DeviceType;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author SV
 */
public class ListDeviceResultRowMapper implements RowMapper<List<DeviceResponse>> {

    @Override
    public List<DeviceResponse> mapRow(ResultSet rs, int rowNum) throws SQLException {
        List<DeviceResponse> deviceResponses = new ArrayList<>();

        do {
            DeviceResponse deviceResponse = new DeviceResponse();
            deviceResponse.setId(rs.getString("id"));
            deviceResponse.setHouseId(rs.getString("house_id"));
            deviceResponse.setDeviceType(DeviceType.valueOf(rs.getString("r_device_type_code")));
            deviceResponse.setName(rs.getString("name"));
            deviceResponse.setIsEnabled(rs.getBoolean("is_enabled"));
            deviceResponses.add(deviceResponse);
        } while (rs.next());

        return deviceResponses;
    }
}
