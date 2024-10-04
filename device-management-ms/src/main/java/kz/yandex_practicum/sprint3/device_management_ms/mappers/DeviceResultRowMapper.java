/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kz.yandex_practicum.sprint3.device_management_ms.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import kz.yandex_practicum.sprint3.device_management_ms.dto.DeviceResponse;
import kz.yandex_practicum.sprint3.device_management_ms.dto.DeviceType;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author SV
 */
public class DeviceResultRowMapper  implements RowMapper<DeviceResponse> {
    @Override
    public DeviceResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        DeviceResponse r = new DeviceResponse();

        r.setId(rs.getString("id"));
        r.setHouseId(rs.getString("house_id"));
        r.setDeviceType(DeviceType.valueOf(rs.getString("r_device_type_code")));
        r.setName(rs.getString("name"));
        r.setIsEnabled(rs.getBoolean("is_enabled"));
        r.setExternalId(rs.getString("external_id"));
        
        return r;
    }
}

