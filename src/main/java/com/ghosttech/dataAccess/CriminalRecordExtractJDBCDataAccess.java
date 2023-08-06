package com.ghosttech.dataAccess;

import com.ghosttech.dao.CriminalRecordExtractDao;
import com.ghosttech.model.CriminalRecordExtract;
import com.ghosttech.utils.ConvertData;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.Timestamp;


@Repository()
@AllArgsConstructor
public class CriminalRecordExtractJDBCDataAccess
        implements CriminalRecordExtractDao {

    private final JdbcTemplate jdbcTemplate;
    @Override
    public int insertCriminalRecordExtract(CriminalRecordExtract recordExtract)  {
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
}
