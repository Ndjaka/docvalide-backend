package com.ghosttech.model;
import lombok.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LegalizationDoc {

    private UUID id;
    private int quantity;
    private String fileUrl;
    private String designation;
    private UUID legalization_id;
}