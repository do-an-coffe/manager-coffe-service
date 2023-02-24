package com.example.coffeebe.app.dtos.request.impl;

import com.example.coffeebe.app.dtos.request.FilterDto;
import com.example.coffeebe.domain.entities.business.ProductSource;
import lombok.Data;

@Data
public class ProductSourceFilterDto implements FilterDto<ProductSource> {
}
