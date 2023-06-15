package com.ghosttech.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    @NotBlank(message = "First name cannot be blank")
    private String firstname;
    @NotBlank(message = "Last name cannot be blank")
    private String lastname;
    @NotBlank(message = "Email address cannot be blank")
    @Email(message = "Email address must be valid")
    private String email;
    private String password;
    @NotBlank(message = "Phone number cannot be blank")
    private String phoneNumber;
    @NotBlank(message = "Town of residence cannot be blank")
    private String townOfResidence;

    private String roles;
    private String occupation;


}
