package com.ghosttech.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Builder
@Setter
@Getter
public class LegalizationRequest {
    @NotBlank(message = "motivation can not be blank")
    @NotNull(message = "motivation can not be null")
    private String motif;

    @NotBlank(message = "receipt moment is required")
    @NotNull(message = "receipt can not be null")
    private String receipMoment;
    private UUID userId;
    List<LegalizationDocRequest> legalizationDocs;

}
