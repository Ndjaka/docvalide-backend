package com.ghosttech.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LegalizationDocRequest {
    private int quantity;
    private String fileUrl;
    private String fileName;
    private String designation;
}
