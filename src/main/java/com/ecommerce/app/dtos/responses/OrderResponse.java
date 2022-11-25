package com.ecommerce.app.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private Integer quantity;

    private ProductResponse product;

    private Long amount;

    private DiscountResponse discount;
}
