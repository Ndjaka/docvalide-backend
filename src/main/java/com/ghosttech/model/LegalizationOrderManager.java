package com.ghosttech.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LegalizationOrderManager {
    private Orders orders;
    private Legalization legalization;
    private User user;
}
