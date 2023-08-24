package com.ghosttech.mapper.rowMapper;

import com.ghosttech.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return  new User(
                rs.getObject("id", java.util.UUID.class),
                rs.getString("firstname"),
                rs.getString("lastname"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("phone_number"),
                rs.getString("town_of_residence"),
                rs.getBoolean("is_active"),
                rs.getString("roles"),
                rs.getString("occupation"),
                rs.getTimestamp("createdDate").toInstant()
        );
    }
}
