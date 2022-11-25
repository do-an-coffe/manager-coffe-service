package com.ecommerce.app.dtos.request.impl;


import com.ecommerce.app.dtos.request.FilterDto;
import com.ecommerce.domain.entities.business.Discount;
import lombok.Data;

@Data
public class DiscountFilterDto implements FilterDto<Discount> {
    private Long productId;
}
