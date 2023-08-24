package com.ghosttech.dataAccess;

import com.ghosttech.dao.LegalizationDocDao;
import com.ghosttech.mapper.rowMapper.LegalizationDocRowMapper;
import com.ghosttech.model.LegalizationDoc;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository()
@AllArgsConstructor
public class LegalizationDocJDBCDataAccess
        implements LegalizationDocDao {

    JdbcTemplate jdbcTemplate;

    @Override
    public int insertLegalizationDoc(LegalizationDoc legalizationDoc) {

        String sql = """
                INSERT INTO legalization_docs(
                id,
                quantity,
                file_url,
                designation,
                legalization_id
                )
                VALUES(?,?,?,?,?)
                """;

        return jdbcTemplate.update(
                sql,
                legalizationDoc.getId(),
                legalizationDoc.getQuantity(),
                legalizationDoc.getFileUrl(),
                legalizationDoc.getDesignation(),
                legalizationDoc.getLegalization_id()
        );
    }

    @Override
    public List<LegalizationDoc> selectLegalizationDocById(UUID legalizationId) {

        String sql = """
                SELECT
                id,
                quantity,
                file_url,
                designation,
                legalization_id
                FROM legalization_docs
                WHERE legalization_id = ?
                """;

        return  jdbcTemplate.query(sql,new LegalizationDocRowMapper(),legalizationId);
    }
}
