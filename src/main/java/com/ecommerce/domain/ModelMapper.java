package com.ecommerce.domain;

import com.ecommerce.app.responses.UserResponse;
import com.ecommerce.domain.entities.author.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ModelMapper {

  UserResponse toUserResponse(User user);
}
