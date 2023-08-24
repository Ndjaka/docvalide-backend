package com.ghosttech.dataAccess;

import com.ghosttech.constants.DocValidConstant;
import com.ghosttech.dao.CriminalRecordExtractDao;
import com.ghosttech.mapper.rowMapper.CriminalRecordRowMapper;
import com.ghosttech.model.CriminalRecordExtract;
import com.ghosttech.model.CriminalRecordExtractManager;
import com.ghosttech.utils.ConvertData;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;


@Repository()
@AllArgsConstructor
public class CriminalRecordExtractJDBCDataAccess
        implements CriminalRecordExtractDao {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public int insertCriminalRecordExtract(CriminalRecordExtract recordExtract) {
        String sql = """
                    INSERT INTO criminalRecordExtract(
                        id,
                        isEstablished,
                        mother_name,
                        birth_department,
                    date,
                    file_url,
                        user_id
                    )
                    VALUES (?, ?, ?, ?, ?, ?, ?);
                """;

        return jdbcTemplate.update(sql,
                recordExtract.getId(),
                recordExtract.isEstablished(),
                recordExtract.getMotherName(),
                recordExtract.getBirthDepartment(),
                Timestamp.from(recordExtract.getDate()),
                ConvertData.objectToString(recordExtract.getFileUrl()),
                recordExtract.getUserId());
    }

    @Override
    public List<CriminalRecordExtractManager> selectCriminalRecordExtractByOrdersAndUser() {

        String sql = """
                SELECT
                orders.id as orderid,
                orders.order_date,
                orders.order_status,
                orders.order_amount,
                orders.order_number,
                u.id as userid,
                u.firstname,
                u.lastname,
                u.phone_number,
                u.email,
                u.town_of_residence,
                u.is_active,
                c.id as criminalRecordid,
                c.mother_name,
                c.birth_department,
                c.date as criminalRecordDate,
                c.file_url
                FROM orders
                JOIN users u ON orders.user_id = u.id
                JOIN criminalrecordextract c ON u.id = c.user_id
                WHERE orders.ordertype = 'criminal_record'
                ORDER BY orders.order_date DESC
                """;

        return jdbcTemplate.query(sql, new Object[]{DocValidConstant.CRIMINAL_RECORD}, new CriminalRecordRowMapper());


    }


}
