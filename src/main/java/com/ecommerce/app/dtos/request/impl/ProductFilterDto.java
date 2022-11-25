package com.ecommerce.app.dtos.request.impl;

import com.ecommerce.app.dtos.request.FilterDto;
import com.ecommerce.domain.entities.business.Product;
import lombok.Data;

@Data
public class ProductFilterDto implements FilterDto<Product> {

    private Long categoryId;

    private String categoryName;

    private String productName;

    private String sort;
}
