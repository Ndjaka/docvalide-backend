package com.ghosttech.dao;

import com.ghosttech.model.Legalization;
import com.ghosttech.model.LegalizationOrderManager;

import java.util.List;

public interface LegalizationDao {
    int insertLegalization(Legalization legalization);

    List<LegalizationOrderManager> selectLegalizationOrdersWithUserAndDetailsOrderedByDate(String firstName);
}
