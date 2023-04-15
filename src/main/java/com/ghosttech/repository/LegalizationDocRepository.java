package com.ghosttech.repository;

import com.ghosttech.dao.LegalizationDocDao;
import com.ghosttech.model.LegalizationDoc;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class LegalizationDocRepository implements LegalizationDocDao {

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
}
