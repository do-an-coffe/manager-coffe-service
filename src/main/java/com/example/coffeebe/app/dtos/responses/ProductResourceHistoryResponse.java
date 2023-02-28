package com.example.coffeebe.app.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResourceHistoryResponse {
  private Long id;
  private ProductResponse product;
  private ProductSourceResponse productSource;
  private Integer preQuantity;
  private Integer currentQuantity;

}
