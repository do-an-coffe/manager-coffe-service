package com.ecommerce.app.dtos.impl;

import com.ecommerce.app.dtos.FilterDto;
import com.ecommerce.domain.entities.business.Product;
import lombok.Data;

@Data
public class ProductFilterDto implements FilterDto<Product> {

    private Long categoryId;

    private String categoryName;

    private String productName;

    private String sort;
}
