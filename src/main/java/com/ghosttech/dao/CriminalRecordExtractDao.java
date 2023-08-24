package com.ghosttech.dao;
import com.ghosttech.mapper.rowMapper.CriminalRecordRowMapper;
import com.ghosttech.model.CriminalRecordExtract;
import com.ghosttech.model.CriminalRecordExtractManager;

import java.util.List;


public interface CriminalRecordExtractDao {
    int insertCriminalRecordExtract(CriminalRecordExtract recordExtract);

    List<CriminalRecordExtractManager>  selectCriminalRecordExtractByOrdersAndUser();
}
