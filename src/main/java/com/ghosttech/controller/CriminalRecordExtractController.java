package com.ghosttech.controller;

import com.ghosttech.dto.CriminalRecordExtractRequest;
import com.ghosttech.dto.Response;
import com.ghosttech.service.CriminalRecordService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/criminalrecord")
public class CriminalRecordExtractController {
    private final CriminalRecordService criminalRecordService;

    @PostMapping
    public ResponseEntity<Response> addCriminalRecord(@Valid @RequestBody CriminalRecordExtractRequest recordExtractRequest){
        var criminalRecord = criminalRecordService.addCriminalRecord(recordExtractRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED).
                body(Response.builder()
                        .message("criminal record is created correcty")
                        .data(criminalRecord)
                        .build()
                );
    }
}
