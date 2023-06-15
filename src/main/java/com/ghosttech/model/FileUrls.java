package com.ghosttech.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FileUrls {
    @NotBlank(message = "front url  cannot be empty")
    private String frontUrl;
    @NotBlank(message = "back url cannot be empty")
    private String backUrl;
    private String fileName;
}
