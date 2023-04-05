package com.ghosttech.controller;

import com.ghosttech.dto.Response;
import com.ghosttech.dto.UserRequest;
import com.ghosttech.service.UserService;
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
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;
    @PostMapping
    public ResponseEntity<Response> addUser(@Valid @RequestBody UserRequest userRequest){
        var user = userService.addUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(Response.builder()
                .message("user is created suscessfully")
                .data(user)
                .build()
        );
    }
}
