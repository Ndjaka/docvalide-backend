package com.ghosttech.mapper.rowMapper;

import com.ghosttech.model.LegalizationDoc;
import com.ghosttech.utils.FileManager;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LegalizationDocRowMapper implements RowMapper<LegalizationDoc> {
    @Override
    public LegalizationDoc mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new LegalizationDoc(
                rs.getObject("id", java.util.UUID.class),
                rs.getInt("quantity"),
                FileManager.getFile(rs.getString("file_url")),
                rs.getString("designation"),
                rs.getObject("legalization_id", java.util.UUID.class)
        );
    }
}
