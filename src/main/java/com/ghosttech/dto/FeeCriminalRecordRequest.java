package com.ghosttech.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@lombok.Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeeCriminalRecordRequest {
    public String residence;
    public String tribunal;
    public String region;
    public Integer fees;

}
