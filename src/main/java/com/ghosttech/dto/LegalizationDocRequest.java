package com.ghosttech.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LegalizationDocRequest {
    @NotBlank( message = "quantity is required")
    private int quantity;

    @NotNull(message = "fileUrl can not be null")
    @NotBlank( message = "fileUrl is required")
    private String fileUrl;

    @NotNull(message = "fileName can not be null")
    @NotBlank( message = "fileName is required")
    private String fileName;

    @NotNull(message = "designation can not be null")
    @NotBlank( message = "designation is required")
    private String designation;
}
