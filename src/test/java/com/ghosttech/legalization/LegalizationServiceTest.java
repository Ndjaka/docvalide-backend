package com.ghosttech.legalization;

import com.ghosttech.dao.LegalizationDao;
import com.ghosttech.dao.LegalizationDocDao;
import com.ghosttech.service.LegalizationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LegalizationServiceTest {

    @Autowired
    LegalizationDocDao legalizationDocDao;
    @Autowired
    LegalizationDao legalizationDao;

    @Test
    public void canCreateLegalization(){
        LegalizationService legalizationService = new LegalizationService(legalizationDao, legalizationDocDao);

    }
}
