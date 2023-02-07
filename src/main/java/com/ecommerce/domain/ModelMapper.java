package com.ecommerce.domain;

import com.ecommerce.app.responses.*;
import com.ecommerce.domain.entities.author.User;
import com.ecommerce.domain.entities.business.*;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ModelMapper {

  UserResponse toUserResponse(User user);

  CategoryResponse toCategoryResponse(Category category);
  ProductResponse toProductResponse(Product product);

  DiscountResponse toDiscountResponse(Discount discount);

  OrderResponse toOrderResponse(Order order);

  SliderResponse toSliderResponse(Slider slider);
  ProductRatingResponse toProductRatingResponse(ProductRating productRating);

  TransactionResponse toTransactionResponse(Transaction transaction);
}
