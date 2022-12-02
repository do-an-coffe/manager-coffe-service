package com.ecommerce.domain;

import com.ecommerce.app.responses.CategoryResponse;
import com.ecommerce.app.responses.UserResponse;
import com.ecommerce.domain.entities.author.User;
import com.ecommerce.domain.entities.business.Category;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ModelMapper {

  UserResponse toUserResponse(User user);

  CategoryResponse toCategoryResponse(Category category);
}
