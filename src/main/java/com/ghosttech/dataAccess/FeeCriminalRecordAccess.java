package com.ghosttech.dataAccess;

import com.ghosttech.dao.FeeCriminalRecordDao;
import com.ghosttech.dto.FeeCriminalRecordRequest;
import com.ghosttech.mapper.rowMapper.FeeCriminalRecordMapper;
import com.ghosttech.model.FeeCriminalRecord;
import com.ghosttech.model.PaginationResult;
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
    public PaginationResult<FeeCriminalRecord> selectFeeCriminalRecordByCityAndTribunalWithPagination(String city, String tribunal, int resultsPerPage, int page) {

        int offset = (page - 1) * resultsPerPage;

        String sql = """
                    SELECT
                    *
                    FROM fees_criminal_record
                    WHERE residence LIKE ? AND tribunal LIKE ?
                    ORDER BY residence, tribunal DESC LIMIT ? OFFSET ?;
                """;

        String countSql = """
                        SELECT COUNT(*) FROM fees_criminal_record
                        WHERE residence LIKE ? AND tribunal LIKE ?;
                    """;

        int totalResults = jdbcTemplate.queryForObject(
                countSql,
                new Object[]{"%" + city + "%", "%" + tribunal + "%"},
                new int[]{Types.VARCHAR, Types.VARCHAR},
                Integer.class
        );

        int totalPages = (int) Math.ceil((double) totalResults / resultsPerPage);

        List<FeeCriminalRecord> results = jdbcTemplate.query(
                sql,
                new Object[]{"%" + city + "%", "%" + tribunal + "%", resultsPerPage, offset},
                new int[]{Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.INTEGER},
                feeCriminalRecordMapper
        );

        PaginationResult<FeeCriminalRecord> paginationResult = new PaginationResult<>();
        paginationResult.setResults(results);
        paginationResult.setPage(page);
        paginationResult.setTotalPages(totalPages);

        return paginationResult;
    }


    @Override
    public Optional<FeeCriminalRecord> selectFeeCriminalRecordById(UUID id) {
        String sql = """
                        SELECT
                        *
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

    @Override
    public void updateFeeCriminalRecordStatus(boolean status, UUID id) {

            String sql = """
                        UPDATE fees_criminal_record SET status = ? WHERE id = ?;
                    """;
            int result = jdbcTemplate.update(sql,status,id);

            System.out.println("update feecriminalrecord status: " + result);
    }
}
