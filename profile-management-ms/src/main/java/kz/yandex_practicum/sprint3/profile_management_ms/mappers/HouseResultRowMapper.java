package kz.yandex_practicum.sprint3.profile_management_ms.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import kz.yandex_practicum.sprint3.profile_management_ms.dto.HouseResponse;

public class HouseResultRowMapper implements RowMapper<HouseResponse> {

    @Override
    public HouseResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        HouseResponse houseResponse = new HouseResponse();
        houseResponse.setId(rs.getString("id"));
        houseResponse.setHouseholderId(rs.getString("householder_id"));
        houseResponse.setAddress(rs.getString("address"));
        houseResponse.setIsEnabled(rs.getBoolean("is_enabled"));
        return houseResponse;
    }
}
