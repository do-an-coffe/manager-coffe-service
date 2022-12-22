package com.ecommerce.app.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CategoryDto{

  private Long id;

  @NotNull(message = "Name not null")
  private String name;

  @NotNull(message = "position not null")
  private Integer position;

  @NotNull(message = "ProductDetail not null")
  @JsonProperty("product_detail")
  private String productDetail;

}
