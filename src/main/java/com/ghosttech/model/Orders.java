package com.ghosttech.model;

import java.time.Instant;
import java.util.UUID;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Orders {

    private UUID id;
    private String orderType;
    private Instant orderDate;
    private String orderStatus;
    private int orderAmount;
    private String orderNumber;
    private UUID userId;

}
