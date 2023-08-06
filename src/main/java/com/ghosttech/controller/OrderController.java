package com.ghosttech.controller;


import com.ghosttech.dto.OrderRequest;
import com.ghosttech.dto.Response;
import com.ghosttech.service.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/order")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Response> addOrder( @Valid @RequestBody OrderRequest orderRequest , @RequestParam("userId") UUID userId){
        orderService.addOrder(orderRequest, userId);
        return ResponseEntity
                .status(HttpStatus.CREATED).
                body(Response.builder()
                        .message("order is created correcty")
                        .build()
                );
    }


}
