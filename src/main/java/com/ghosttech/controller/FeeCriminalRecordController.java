package com.ghosttech.controller;

import com.ghosttech.dto.FeeCriminalRecordRequest;
import com.ghosttech.model.FeeCriminalRecord;
import com.ghosttech.service.FeeCriminalRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/feeCriminalRecord")
public class FeeCriminalRecordController {

    private final FeeCriminalRecordService feeCriminalRecordService;

    public FeeCriminalRecordController(FeeCriminalRecordService feeCriminalRecordService) {
        this.feeCriminalRecordService = feeCriminalRecordService;
    }

    @PostMapping
    public ResponseEntity<FeeCriminalRecord> addFeeCriminalRecord(FeeCriminalRecordRequest feeCriminalRecordRequest){
        var  feeCriminalRecord = feeCriminalRecordService.saveFeeCriminalRecord(feeCriminalRecordRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(feeCriminalRecord);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeeCriminalRecord> updateFeeCriminalRecord(FeeCriminalRecordRequest feeCriminalRecordRequest,@PathVariable("id") UUID id){
        var  feeCriminalRecord = feeCriminalRecordService.updateFeeCriminalRecord(feeCriminalRecordRequest, id);
        return ResponseEntity.status(HttpStatus.OK).body(feeCriminalRecord);
    }

    @GetMapping
    public ResponseEntity<List<FeeCriminalRecord>> listFeeCriminalRecordByCityAndTribunal(@RequestParam(name = "city", value = "") String city, @RequestParam(name = "tribunal", value = "") String tribunal){
        var  recordList = feeCriminalRecordService.getListFeeCriminalRecordByCityAndTribunal(city, tribunal);
        return ResponseEntity.status(HttpStatus.OK).body(recordList);
    }
}
