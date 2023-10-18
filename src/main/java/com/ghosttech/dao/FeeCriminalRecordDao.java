package com.ghosttech.dao;


import com.ghosttech.dto.FeeCriminalRecordRequest;
import com.ghosttech.model.FeeCriminalRecord;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FeeCriminalRecordDao {
    void insertFeeCriminalRecord(FeeCriminalRecord feeCriminalRecord);

    void updateFeeCriminalRecord(FeeCriminalRecord feeCriminalRecord);

    List<FeeCriminalRecord> selectFeeCriminalRecordByCityAndTribunal(String city, String tribunal);

    Optional<FeeCriminalRecord> selectFeeCriminalRecordById(UUID id);
}
