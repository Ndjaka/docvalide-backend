package com.ghosttech.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
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