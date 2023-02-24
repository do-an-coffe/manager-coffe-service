package com.example.coffeebe.app.dtos.request.impl;

import com.example.coffeebe.app.dtos.request.DTO;
import com.example.coffeebe.domain.entities.business.ProductSource;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProductSourceDto implements DTO<ProductSource> {
  private Long id;

  @NotNull(message = "product_id not null")
  @JsonProperty("product_id")
  private Long productId;

  @NotNull(message = "quantity not null")
  private Integer quantity;
}
