package com.ghosttech.controller;

import com.ghosttech.dto.LegalizationRequest;
import com.ghosttech.dto.Response;
import com.ghosttech.service.LegalizationService;
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
@RequestMapping("api/v1/legalization")
public class LegalizationController {

    private final LegalizationService legalizationService;

    @PostMapping
    public ResponseEntity<Response> addLegalization(@Valid @RequestBody LegalizationRequest legalizationRequest){
        legalizationService.addLegalization(legalizationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(Response.builder()
                .message("legalization is created suscessfully")
                .build());
    }
}
