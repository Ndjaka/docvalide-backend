package com.ghosttech.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileUrls {
    @NotBlank(message = "front url  cannot be empty")
    private String frontUrl;
    @NotBlank(message = "back url cannot be empty")
    private String backUrl;
    private String fileName;
}
