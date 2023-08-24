package com.ghosttech.service;

import com.ghosttech.dao.LegalizationDao;
import com.ghosttech.dao.LegalizationDocDao;
import com.ghosttech.dto.LegalizationRequest;
import com.ghosttech.model.Legalization;
import com.ghosttech.model.LegalizationDoc;
import com.ghosttech.model.LegalizationOrderManager;
import com.ghosttech.utils.FileManager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class LegalizationService {
    private final LegalizationDao legalizationDao;
    private final LegalizationDocDao legalizationDocDao;

    /**
     * Add legalization.
     * @param legalizationRequest the legalization request
     *
     */
    @Transactional
    public void addLegalization(LegalizationRequest legalizationRequest) {
        log.info("starting create legalization.......");

        //TODO: check if user
        UUID legalizationId = UUID.randomUUID();

        var legalization = Legalization.builder()
                .id(legalizationId)
                .isLegalized(false)
                .quantity(legalizationRequest.getQuantity())
                .motif(legalizationRequest.getMotif())
                .receipMoment(legalizationRequest.getReceiptMoment())
                .userId(legalizationRequest.getUserId())
                .date(Instant.now())
                .build();

       int insertLegalizationId = legalizationDao.insertLegalization(legalization);
       if(insertLegalizationId != 1) throw new IllegalStateException("something went wrong when legalization was insert");

       legalizationRequest.getLegalizationDocs().forEach(request -> {

           FileManager.base64ToFileAndSaveToDirectory(request.getFileUrl(),request.getFileName());

           var legalizationdoc = LegalizationDoc.builder()
                   .id(UUID.randomUUID())
                   .designation(request.getDesignation())
                   .quantity(request.getQuantity())
                   .fileUrl(request.getFileName())
                   .legalization_id(legalizationId)
                   .build();

           int insertLegalizationDocId = legalizationDocDao.insertLegalizationDoc(legalizationdoc);
           if(insertLegalizationDocId != 1) throw new IllegalStateException("something went wrong when legalizationDoc was insert");

       });

        log.info("create legalization is done....");
    }

    public List<LegalizationOrderManager> getLegalizationOrdersWithUserAndDetailsOrderedByDate() {
        return legalizationDao.selectLegalizationOrdersWithUserAndDetailsOrderedByDate();
    }

    public List<LegalizationDoc> getLegalizationDocsByLegalizationId(UUID legalizationId) {
        return legalizationDocDao.selectLegalizationDocById(legalizationId);
    }
}
