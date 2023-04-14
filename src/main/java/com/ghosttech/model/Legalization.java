package com.ghosttech.model;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Legalization {
    private UUID id;
    private String motif;

    private String receipMoment;
    private boolean isLegalized;

    private UUID userId;
    private Instant date;
}