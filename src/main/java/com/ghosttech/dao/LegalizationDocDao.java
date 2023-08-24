package com.ghosttech.dao;

import com.ghosttech.mapper.rowMapper.LegalizationDocRowMapper;
import com.ghosttech.model.LegalizationDoc;

import java.util.List;
import java.util.UUID;

public interface LegalizationDocDao {

    int insertLegalizationDoc(LegalizationDoc legalizationDoc);

    List<LegalizationDoc> selectLegalizationDocById(UUID legalizationId);
}
