package com.ghosttech.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Builder
@Setter
@Getter
public class LegalizationRequest {

    private String motif;
    private String receipMoment;
    private boolean isLegalized;
    private UUID userId;
    List<LegalizationDocRequest> legalizationDocs;

}
