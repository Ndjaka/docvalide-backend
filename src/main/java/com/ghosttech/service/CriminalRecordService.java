package com.ghosttech.service;

import com.ghosttech.constants.DocValidConstant;
import com.ghosttech.dao.CriminalRecordExtractDao;
import com.ghosttech.dto.CriminalRecordExtractRequest;
import com.ghosttech.dto.CriminalRecordExtractResponse;
import com.ghosttech.mapper.CriminalRecordDTOMapper;
import com.ghosttech.model.CriminalRecordExtract;
import com.ghosttech.model.FileUrls;
import com.ghosttech.utils.FileManager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class CriminalRecordService {
    private final CriminalRecordExtractDao extractDao;
    private final CriminalRecordDTOMapper criminalRecordDTOMapper;

    /**
     * Create the files, add criminalRecordExtract in database and add these files in Directory of the documents
     * @param criminalRecordRequest - data who are coming of the client is the object who represent criminalRecord
     * @return CriminalRecordExtractResponse - response who is returning of the client to confirm creating data in database
     */
    public CriminalRecordExtractResponse addCriminalRecord(CriminalRecordExtractRequest criminalRecordRequest)  {

        log.info("start creating criminal record...");

        String FrontFileName = DocValidConstant.FRONT_FILE_NAME + criminalRecordRequest.getFileUrl().getFileName();
        String BackFileName =  DocValidConstant.BACK_FILE_NAME + criminalRecordRequest.getFileUrl().getFileName();

        var criminalRecord = CriminalRecordExtract.builder()
                .id(UUID.randomUUID())
                .birthDepartment(criminalRecordRequest.getBirthDepartment())
                .date(Instant.now())
                .isEstablished(false)
                .motherName(criminalRecordRequest.getMotherName())
                .userId(criminalRecordRequest.getUserId())
                .fileUrl(FileUrls.builder()
                        .backUrl(BackFileName)
                        .frontUrl(FrontFileName)
                        .build())
                .build();

        FileManager.base64ToFileAndSaveToDirectory( criminalRecordRequest.getFileUrl().getFrontUrl(), FrontFileName);
        FileManager.base64ToFileAndSaveToDirectory(criminalRecordRequest.getFileUrl().getBackUrl(), BackFileName);

        int result = extractDao.insertCriminalRecordExtract(criminalRecord);
        if(result != 1) throw  new IllegalStateException("oops something wrong");

        log.info("end of creating criminal record...");

        return criminalRecordDTOMapper.apply(criminalRecord);
    }
}
