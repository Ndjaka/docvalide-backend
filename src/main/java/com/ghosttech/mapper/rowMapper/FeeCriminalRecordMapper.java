package com.ghosttech.mapper.rowMapper;

import com.ghosttech.model.FeeCriminalRecord;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;


import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class FeeCriminalRecordMapper  implements RowMapper<FeeCriminalRecord> {

    @Override
    public FeeCriminalRecord mapRow(ResultSet rs, int rowNum) throws SQLException {

        return new FeeCriminalRecord(
                rs.getObject("id", java.util.UUID.class),
                rs.getString("residence"),
                rs.getString("tribunal"),
                rs.getInt("fees"),
                rs.getBoolean("status")

        );
    }
}
