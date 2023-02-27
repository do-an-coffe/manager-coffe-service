package com.example.coffeebe.app.dtos.request.impl;

import com.example.coffeebe.domain.entities.enums.ProductResourceState;
import lombok.Data;

@Data
public class ProductSourceStateDto {
  private ProductResourceState state;
}
