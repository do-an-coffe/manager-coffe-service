package com.ecommerce.app.dtos.impl;

import lombok.Data;

@Data
public class ProductFilterDto {

    private Long categoryId;

    private String categoryName;

    private String productName;

    private String sort;
}
