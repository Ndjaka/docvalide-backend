package com.ghosttech.controller;

import com.ghosttech.dto.FeeCriminalRecordListRequest;
import com.ghosttech.dto.FeeCriminalRecordRequest;
import com.ghosttech.model.FeeCriminalRecord;
import com.ghosttech.model.PaginationResult;
import com.ghosttech.service.FeeCriminalRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("api/v1/feeCriminalRecord")
@RestController
public class FeeCriminalRecordController {

    private final FeeCriminalRecordService feeCriminalRecordService;

    public FeeCriminalRecordController(FeeCriminalRecordService feeCriminalRecordService) {
        this.feeCriminalRecordService = feeCriminalRecordService;
    }

    @PostMapping
    public ResponseEntity<FeeCriminalRecord> addFeeCriminalRecord(@RequestBody FeeCriminalRecordRequest feeCriminalRecordRequest){
        var  feeCriminalRecord = feeCriminalRecordService.saveFeeCriminalRecord(feeCriminalRecordRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(feeCriminalRecord);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeeCriminalRecord> updateFeeCriminalRecord(@RequestBody FeeCriminalRecordRequest feeCriminalRecordRequest,@PathVariable("id") UUID id){
        var  feeCriminalRecord = feeCriminalRecordService.updateFeeCriminalRecord(feeCriminalRecordRequest, id);
        return ResponseEntity.status(HttpStatus.OK).body(feeCriminalRecord);
    }

    @GetMapping
    public ResponseEntity<PaginationResult<FeeCriminalRecord>> listFeeCriminalRecordByCityAndTribunalWithPagination(@ModelAttribute FeeCriminalRecordListRequest request){

        PaginationResult<FeeCriminalRecord> feeCriminalRecords;

        if(request.isWithLimit()){
            feeCriminalRecords = feeCriminalRecordService.selectFeeCriminalRecordByCityAndTribunalWithPagination(request.getCity(), request.getTribunal(), request.getResultsPerPage(), request.getPage(), true);
        }else{
            feeCriminalRecords = feeCriminalRecordService.selectFeeCriminalRecordByCityAndTribunalWithPagination(request.getCity(), request.getTribunal(), 0, 0, false);
        }
        return ResponseEntity.status(HttpStatus.OK).body(feeCriminalRecords);

    }


    @PutMapping("/{id}/{status}")
    public ResponseEntity<FeeCriminalRecord> updateFeeCriminalRecordStatus(@PathVariable("id") UUID id , @PathVariable("status") boolean status){
        var feeCriminalRecord = feeCriminalRecordService.updateFeeCriminalRecordStatus(status,id);
        return ResponseEntity.status(HttpStatus.OK).body(feeCriminalRecord);
    }
}
