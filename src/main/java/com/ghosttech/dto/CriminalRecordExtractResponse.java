package com.ghosttech.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CriminalRecordExtractResponse {

    private String motherName;
    private String birthDepartment;

    private String frontUrl;
    private String backUrl;

}
