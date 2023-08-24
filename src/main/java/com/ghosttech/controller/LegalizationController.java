package com.ghosttech.controller;

import com.ghosttech.dto.LegalizationRequest;
import com.ghosttech.dto.Response;
import com.ghosttech.model.LegalizationDoc;
import com.ghosttech.model.LegalizationOrderManager;
import com.ghosttech.service.LegalizationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/legalization")
public class LegalizationController {

    private final LegalizationService legalizationService;

    @PostMapping
    public ResponseEntity<Response> addLegalization(@Valid @RequestBody LegalizationRequest legalizationRequest){
        legalizationService.addLegalization(legalizationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(Response.builder()
                .message("legalization is created successfully")
                .build());
    }

    @GetMapping
    public ResponseEntity<List<LegalizationOrderManager>>  listLegalizationOrdersWithUserAndDetailsOrderedByDate(){
        var legalization = legalizationService.getLegalizationOrdersWithUserAndDetailsOrderedByDate();
        return ResponseEntity.status(HttpStatus.OK).body(legalization);
    }

    @GetMapping("/legalization-docs/{legalizationId}")
    public ResponseEntity<List<LegalizationDoc>> listLegalizationDocs(@PathVariable("legalizationId") UUID legalizationId){
        var legalizationDocs = legalizationService.getLegalizationDocsByLegalizationId(legalizationId);
        return ResponseEntity.status(HttpStatus.OK).body(legalizationDocs);
    }
}
