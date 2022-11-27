package com.ecommerce.app.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRatingResponse {

    private Long id;

    private ProductResponse product;

    private UserResponse user;

    private String content;

    private Integer numberStar;

}
