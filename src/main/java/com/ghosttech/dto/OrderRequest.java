package com.ghosttech.dto;

import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {
    private String orderType;
    private int orderAmount;
    private String orderStatus;
    private String orderNumber;

}
