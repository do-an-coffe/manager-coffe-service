package com.ecommerce.app.dtos.impl;


import com.ecommerce.app.dtos.FilterDto;
import com.ecommerce.domain.entities.business.Discount;
import lombok.Data;

@Data
public class DiscountFilterDto implements FilterDto<Discount> {
    private Long productId;
}
