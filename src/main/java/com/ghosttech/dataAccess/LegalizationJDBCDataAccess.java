package com.ghosttech.dataAccess;

import com.ghosttech.dao.LegalizationDao;
import com.ghosttech.model.Legalization;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository()
@AllArgsConstructor
public class LegalizationJDBCDataAccess
        implements LegalizationDao {
    private final JdbcTemplate jdbcTemplate;
    @Override
    public int insertLegalization(Legalization legalization) {

        String sql = """
                        INSERT INTO legalization(
                            id,
                            motif,
                            receip_moment,
                            isLegalized,
                            quantity,
                            user_id,
                            date
                        )
                        VALUES (?, ?, ?, ?, ?, ?, ?);
                    """;

        return jdbcTemplate.update(
                sql,
                legalization.getId(),
                legalization.getMotif(),
                legalization.getReceipMoment(),
                legalization.isLegalized(),
                legalization.getQuantity(),
                legalization.getUserId(),
                Timestamp.from(legalization.getDate())
        );

    }
}
