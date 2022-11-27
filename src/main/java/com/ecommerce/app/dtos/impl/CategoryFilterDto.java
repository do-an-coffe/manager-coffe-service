package com.ecommerce.app.dtos.impl;

import com.ecommerce.app.dtos.FilterDto;
import com.ecommerce.domain.entities.business.Category;
import lombok.Data;

@Data
public class CategoryFilterDto implements FilterDto<Category> {

}
