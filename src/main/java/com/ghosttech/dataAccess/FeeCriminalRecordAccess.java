package com.ghosttech.dataAccess;

import com.ghosttech.dao.FeeCriminalRecordDao;
import com.ghosttech.mapper.rowMapper.FeeCriminalRecordMapper;
import com.ghosttech.model.FeeCriminalRecord;
import com.ghosttech.model.PaginationResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
@AllArgsConstructor
@Slf4j
public class FeeCriminalRecordAccess implements FeeCriminalRecordDao {

    private final JdbcTemplate jdbcTemplate;
    private final FeeCriminalRecordMapper feeCriminalRecordMapper;
    @Override
    public void insertFeeCriminalRecord(FeeCriminalRecord feeCriminalRecord) {

        log.info("insertFeeCriminalRecord"+feeCriminalRecord.toString());

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

        log.info("insertFeeCriminalRecord result: " + result);
    }

    @Override
    public void updateFeeCriminalRecord(FeeCriminalRecord feeCriminalRecord) {


        if(feeCriminalRecord.getResidence() != null){
            String sql = """
                        UPDATE fees_criminal_record SET residence = ? WHERE id = ?;
                    """;
         int result = jdbcTemplate.update(sql, feeCriminalRecord.getResidence(),feeCriminalRecord.getId());

            log.info("update feecriminalrecord residence: " + result);
        }

        if(feeCriminalRecord.getTribunal() != null){
            String sql = """
                        UPDATE fees_criminal_record SET tribunal = ? WHERE id = ?;
                    """;
           int result = jdbcTemplate.update(sql, feeCriminalRecord.getTribunal(),feeCriminalRecord.getId());

            log.info("update feecriminalrecord tribunal: " + result);

        }

    }

    @Override
    public PaginationResult<FeeCriminalRecord> selectFeeCriminalRecordByCityAndTribunalWithPagination(String city, String tribunal, int resultsPerPage, int page, boolean withLimit) {

        int offset = (page - 1) * resultsPerPage;
        boolean isLimit = withLimit && resultsPerPage > 0 && page > 0;

        String sql = """
                SELECT
                *
                FROM fees_criminal_record
                WHERE residence LIKE ? AND tribunal LIKE ?
                ORDER BY residence ASC, tribunal ASC
            """;

        if (isLimit) {
            sql += " LIMIT ? OFFSET ?;";
        }

        String countSql = """
                    SELECT COUNT(*) FROM fees_criminal_record
                    WHERE residence LIKE ? AND tribunal LIKE ?;
                """;

        int totalResults = jdbcTemplate.queryForObject(countSql, new Object[]{"%" + city + "%", "%" + tribunal + "%"}, new int[]{Types.VARCHAR, Types.VARCHAR}, Integer.class);

        int totalPages = (int) Math.ceil((double) totalResults / resultsPerPage);

        List<FeeCriminalRecord> results;

        if (isLimit) {
            results = jdbcTemplate.query(
                    sql,
                    new Object[]{"%" + city + "%", "%" + tribunal + "%", resultsPerPage, offset},
                    new int[]{Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.INTEGER},
                    feeCriminalRecordMapper
            );
        } else {
            results = jdbcTemplate.query(
                    sql,
                    new Object[]{"%" + city + "%", "%" + tribunal + "%"},
                    new int[]{Types.VARCHAR, Types.VARCHAR},
                    feeCriminalRecordMapper
            );
        }

        PaginationResult<FeeCriminalRecord> paginationResult = new PaginationResult<>();
        paginationResult.setResults(results);
        paginationResult.setPage(page);
        paginationResult.setTotalPages(isLimit ? totalPages : 1);

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

        log.info("update feecriminalrecord status: " + result);
    }
}
