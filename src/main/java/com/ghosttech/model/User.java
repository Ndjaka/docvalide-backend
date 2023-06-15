package com.ghosttech.model;

import lombok.*;

import java.time.Instant;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private UUID id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String phoneNumber;
    private String townOfResidence;
    private boolean isActive;
    private String roles;
    private String occupation;
    private Instant createdDate;
}