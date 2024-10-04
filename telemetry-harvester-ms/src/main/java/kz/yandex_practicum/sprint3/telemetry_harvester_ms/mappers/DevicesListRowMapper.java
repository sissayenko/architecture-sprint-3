package kz.yandex_practicum.sprint3.telemetry_harvester_ms.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import kz.yandex_practicum.sprint3.telemetry_harvester_ms.service.DeviceResponse;
import kz.yandex_practicum.sprint3.telemetry_harvester_ms.service.DeviceType;

import org.springframework.jdbc.core.RowMapper;

public class DevicesListRowMapper implements RowMapper<List<DeviceResponse>> {

    @Override
    public List<DeviceResponse> mapRow(ResultSet rs, int rowNum) throws SQLException {
        List<DeviceResponse> devices = new ArrayList<>();

        do {
            DeviceResponse r = new DeviceResponse();
            r.setId(rs.getString("device_id"));
            r.setDeviceType(DeviceType.valueOf(   rs.getString("device_type")));
            devices.add(r);

        } while (rs.next());
        return devices;
    }
}
