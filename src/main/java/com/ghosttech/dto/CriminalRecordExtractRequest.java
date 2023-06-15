package com.ghosttech.dto;

import com.ghosttech.model.FileUrls;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CriminalRecordExtractRequest {

    @NotBlank(message = "The mother's name cannot be empty")
    private String motherName;
    @NotBlank(message = "the department cannot be empty")
    private String birthDepartment;
    private FileUrls fileUrl;

    private UUID userId;

}
