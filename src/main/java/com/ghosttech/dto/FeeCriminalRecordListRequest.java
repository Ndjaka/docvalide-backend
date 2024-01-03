package com.ghosttech.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeeCriminalRecordListRequest {
    private String city = "";
    private String tribunal = "";
    private int resultsPerPage = 10;
    private int page = 1;
    private boolean withLimit = true;
}
