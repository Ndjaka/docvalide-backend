package com.ghosttech.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CriminalRecordExtractResponse {

    private String motherName;
    private String birthDepartment;

    private String frontUrl;
    private String backUrl;

}
