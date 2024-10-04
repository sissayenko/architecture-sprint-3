package kz.yandex_practicum.sprint3.profile_management_ms.mappers;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;
import kz.yandex_practicum.sprint3.profile_management_ms.dto.HouseResponse;

public class ListHouseResultRowMapper implements RowMapper<List<HouseResponse>> {

    @Override
    public List<HouseResponse> mapRow(ResultSet rs, int rowNum) throws SQLException {
        List<HouseResponse> houseResponses = new ArrayList<>();
        do {
            HouseResponse houseResponse = new HouseResponse();
            houseResponse.setId(rs.getString("id"));
            houseResponse.setHouseholderId(rs.getString("householder_id"));
            houseResponse.setAddress(rs.getString("address"));
            houseResponse.setIsEnabled(rs.getBoolean("is_enabled"));
            houseResponses.add(houseResponse);
        } while (rs.next());
        return houseResponses;
    }
}