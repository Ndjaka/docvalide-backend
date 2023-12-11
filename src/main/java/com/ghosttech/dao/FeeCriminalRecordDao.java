package com.ghosttech.dao;


import com.ghosttech.dto.FeeCriminalRecordRequest;
import com.ghosttech.model.FeeCriminalRecord;
import com.ghosttech.model.PaginationResult;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FeeCriminalRecordDao {
    void insertFeeCriminalRecord(FeeCriminalRecord feeCriminalRecord);

    void updateFeeCriminalRecord(FeeCriminalRecord feeCriminalRecord);

    PaginationResult<FeeCriminalRecord> selectFeeCriminalRecordByCityAndTribunalWithPagination(String city, String tribunal, int resultsPerPage, int page);

    Optional<FeeCriminalRecord> selectFeeCriminalRecordById(UUID id);

    void updateFeeCriminalRecordStatus(boolean status, UUID id);
}
