package kz.yandex_practicum.sprint3.profile_management_ms.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import kz.yandex_practicum.sprint3.profile_management_ms.dto.HouseholderResponse;
import org.springframework.jdbc.core.RowMapper;

public class HouseholderResultRowMapper implements RowMapper<HouseholderResponse> {

    @Override
    public HouseholderResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        HouseholderResponse householder = new HouseholderResponse();
        householder.setId(rs.getString("id"));
        householder.setName(rs.getString("name"));
        householder.setEmail(rs.getString("email"));
        householder.setIsEnabled(rs.getBoolean("is_enabled"));
        return householder;
    }
}
