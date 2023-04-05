package com.ghosttech.mapper;


import com.ghosttech.dto.CriminalRecordExtractResponse;
import com.ghosttech.model.CriminalRecordExtract;
import com.ghosttech.utils.FileManager;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class CriminalRecordDTOMapper implements Function<CriminalRecordExtract, CriminalRecordExtractResponse> {

    @Override
    public CriminalRecordExtractResponse apply(CriminalRecordExtract extractRequest) {
        return CriminalRecordExtractResponse.builder()
                .motherName(extractRequest.getMotherName())
                .birthDepartment(extractRequest.getBirthDepartment())
                .backUrl(FileManager.getFile(extractRequest.getFileUrl().getBackUrl()))
                .frontUrl(FileManager.getFile(extractRequest.getFileUrl().getFrontUrl()))
                .build();
    }
}
