package com.ghosttech.dataAccess;

import com.ghosttech.constants.DocValidConstant;
import com.ghosttech.dao.LegalizationDao;
import com.ghosttech.mapper.rowMapper.LegalizationRowMapper;
import com.ghosttech.model.Legalization;
import com.ghosttech.model.LegalizationOrderManager;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

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

    @Override
    public List<LegalizationOrderManager> selectLegalizationOrdersWithUserAndDetailsOrderedByDate(String firstName) {

      String sql = """
                     SELECT
                              o.id as orderId,
                              o.order_date as orderDate, -- Extraction de la date uniquement
                              o.order_status,
                              o.order_amount,
                              o.order_number,
                              u.id as userId,
                              u.firstname,
                              u.lastname,
                              u.phone_number,
                              u.email,
                              u.town_of_residence,
                              u.is_active,
                              l.id as legalizationId,
                              l.motif,
                              l.receip_moment,
                              l.islegalized,
                              l.quantity,
                              l.date as legalizationDate
                          FROM orders o
                          INNER JOIN users u ON u.id = o.user_id
                          INNER JOIN legalization l ON u.id = l.user_id
                          WHERE o.ordertype = ?
                          AND u.firstname LIKE ?
                          ORDER BY  l.date;
                    """;

            return jdbcTemplate.query(sql, new LegalizationRowMapper(), DocValidConstant.LEGALIZATION, "%" + firstName+ "%");
    }
}
