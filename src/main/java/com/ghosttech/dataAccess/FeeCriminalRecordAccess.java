package com.ghosttech.dataAccess;

import com.ghosttech.dao.FeeCriminalRecordDao;
import com.ghosttech.dto.FeeCriminalRecordRequest;
import com.ghosttech.mapper.rowMapper.FeeCriminalRecordMapper;
import com.ghosttech.model.FeeCriminalRecord;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
@AllArgsConstructor
public class FeeCriminalRecordAccess implements FeeCriminalRecordDao {

    private final JdbcTemplate jdbcTemplate;
    private final FeeCriminalRecordMapper feeCriminalRecordMapper;
    @Override
    public void insertFeeCriminalRecord(FeeCriminalRecord feeCriminalRecord) {

        System.out.println("insertFeeCriminalRecord"+feeCriminalRecord.toString());

        String sql = """
                        INSERT INTO fees_criminal_record(
                            id,
                            residence,
                            tribunal,
                            fees
                        )
                        VALUES (?, ?, ?, ?)
                    """;

    int result = jdbcTemplate.update(
                sql,
                feeCriminalRecord.getId(),
                feeCriminalRecord.getResidence(),
                feeCriminalRecord.getTribunal(),
                feeCriminalRecord.getFees()
        );

        // 1 if success, 0 if fail

        System.out.println("insertFeeCriminalRecord result: " + result);
    }

    @Override
    public void updateFeeCriminalRecord(FeeCriminalRecord feeCriminalRecord) {


        if(feeCriminalRecord.getResidence() != null){
            String sql = """
                        UPDATE fees_criminal_record SET residence = ? WHERE id = ?;
                    """;
         int result = jdbcTemplate.update(sql, feeCriminalRecord.getResidence(),feeCriminalRecord.getId());

         System.out.println("update feecriminalrecord residence: " + result);
        }

        if(feeCriminalRecord.getTribunal() != null){
            String sql = """
                        UPDATE fees_criminal_record SET tribunal = ? WHERE id = ?;
                    """;
           int result = jdbcTemplate.update(sql, feeCriminalRecord.getTribunal(),feeCriminalRecord.getId());

            System.out.println("update feecriminalrecord tribunal: " + result);

        }

    }

    @Override
    public List<FeeCriminalRecord> selectFeeCriminalRecordByCityAndTribunal(String city, String tribunal) {

        String sql = """
                        SELECT
                        id,
                        residence,
                        tribunal,
                        fees
                        FROM fees_criminal_record
                        WHERE residence LIKE ? AND tribunal LIKE ?
                        ORDER BY residence ,tribunal;
                    """;

        return jdbcTemplate.query(
                sql,
                new Object[]{"%" + city + "%", "%" + tribunal + "%"},
                new int[]{Types.VARCHAR, Types.VARCHAR},
                feeCriminalRecordMapper
        );
    }

    @Override
    public Optional<FeeCriminalRecord> selectFeeCriminalRecordById(UUID id) {
        String sql = """
                        SELECT
                        id,
                        residence,
                        tribunal,
                        fees
                        FROM fees_criminal_record
                        WHERE id = ?
                    """;

        return jdbcTemplate.query(
                sql,
                new Object[]{id},
                new int[]{Types.OTHER},
                feeCriminalRecordMapper
        ).stream().findFirst();
    }
}
