package com.ghosttech.service;

import com.ghosttech.dao.FeeCriminalRecordDao;
import com.ghosttech.dto.FeeCriminalRecordRequest;
import com.ghosttech.exception.NotFoundException;
import com.ghosttech.exception.RequestValidationException;
import com.ghosttech.model.FeeCriminalRecord;
import com.ghosttech.model.PaginationResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FeeCriminalRecordService {

    private final FeeCriminalRecordDao feeCriminalRecordDao;

   public FeeCriminalRecord saveFeeCriminalRecord(FeeCriminalRecordRequest feeCriminalRecordRequest){

       FeeCriminalRecord feeCriminalRecord = FeeCriminalRecord.builder()
                .id(UUID.randomUUID())
                .residence(feeCriminalRecordRequest.getResidence())
                .tribunal(feeCriminalRecordRequest.getTribunal())
                .fees(feeCriminalRecordRequest.getFees())
                .status(false)
                .build();


       feeCriminalRecordDao.insertFeeCriminalRecord(feeCriminalRecord);

        return feeCriminalRecord;
    }

    public FeeCriminalRecord updateFeeCriminalRecord(FeeCriminalRecordRequest feeCriminalRecordRequest, UUID id){

        FeeCriminalRecord feeCriminalRecord  =  feeCriminalRecordDao.selectFeeCriminalRecordById(id)
                .orElseThrow(() -> new NotFoundException("Fee Criminal Record with id " + id + " does not exist","FEES_CRIMINAL_RECORD_NOT_FOUND"));

        boolean changes = false;

        if(feeCriminalRecordRequest.getResidence() != null && !feeCriminalRecordRequest.getResidence().equals(feeCriminalRecord.getResidence())){
            feeCriminalRecord.setResidence(feeCriminalRecordRequest.getResidence());
            changes = true;
        }

        if(feeCriminalRecordRequest.getTribunal() != null && !feeCriminalRecordRequest.getTribunal().equals(feeCriminalRecord.getTribunal())){
            feeCriminalRecord.setTribunal(feeCriminalRecordRequest.getTribunal());
            changes = true;
        }

        if(feeCriminalRecordRequest.getFees() != null && !feeCriminalRecordRequest.getFees().equals(feeCriminalRecord.getFees())){
            feeCriminalRecord.setFees(feeCriminalRecordRequest.getFees());
            changes = true;
        }



        if(!changes){
            throw new RequestValidationException("No changes were made","NO_CHANGES_MADE");
        }

        feeCriminalRecordDao.updateFeeCriminalRecord(feeCriminalRecord);


        return feeCriminalRecord;
    }

    public FeeCriminalRecord getFeeCriminalRecordById(UUID id){

        return feeCriminalRecordDao.selectFeeCriminalRecordById(id)
                .orElseThrow(() -> new NotFoundException("Fee Criminal Record with id " + id + " does not exist","FEES_CRIMINAL_RECORD_NOT_FOUND"));
    }

    public PaginationResult<FeeCriminalRecord> selectFeeCriminalRecordByCityAndTribunalWithPagination(String city, String tribunal , Integer resultPerPage , Integer page){
        return feeCriminalRecordDao.selectFeeCriminalRecordByCityAndTribunalWithPagination(city, tribunal, resultPerPage, page);
    }

    public FeeCriminalRecord updateFeeCriminalRecordStatus(boolean status,  UUID id){

        FeeCriminalRecord feeCriminalRecord  =  feeCriminalRecordDao.selectFeeCriminalRecordById(id)
                .orElseThrow(() -> new NotFoundException("Fee Criminal Record with id " + id + " does not exist","FEES_CRIMINAL_RECORD_NOT_FOUND"));

        if(feeCriminalRecord.getStatus() != status){
            feeCriminalRecordDao.updateFeeCriminalRecordStatus(status,id);
            feeCriminalRecord.setStatus(status);
        }else{
            throw new RequestValidationException("No update were made","NO_CHANGES_MADE");
        }

        return feeCriminalRecord;
    }


}
