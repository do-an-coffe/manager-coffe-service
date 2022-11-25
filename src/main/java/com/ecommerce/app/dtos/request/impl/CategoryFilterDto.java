package com.ecommerce.app.dtos.request.impl;

import com.ecommerce.app.dtos.request.FilterDto;
import com.ecommerce.domain.entities.business.Category;
import lombok.Data;

@Data
public class CategoryFilterDto implements FilterDto<Category> {

}
