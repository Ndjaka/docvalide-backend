package com.ghosttech.model;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class FeeCriminalRecord {
    private UUID id;
    private String residence;
    private String tribunal;
    private Integer fees;
    private Boolean status;
}
