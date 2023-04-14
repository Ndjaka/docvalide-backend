package com.ghosttech.model;

import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CriminalRecordExtract {
    private UUID id;
    private boolean isEstablished;

    private String motherName;

    private String birthDepartment;

    private Instant date;

    private FileUrls fileUrl;

    private UUID userId;
}