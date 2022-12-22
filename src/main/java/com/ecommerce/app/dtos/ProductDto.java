package com.ecommerce.app.dtos;

import com.ecommerce.app.dtos.DTO;
import com.ecommerce.domain.entities.business.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProductDto{

  private Long id;

  @NotNull(message = "name not null")
  private String name;

  @NotNull(message = "detail not null")
  private String detail;

  @NotNull(message = "price not null")
  private Long price;

  @NotNull(message = "image not null")
  private String image;

  @NotNull(message = "category_id not null")
  @JsonProperty("category_id")
  private Long categoryId;

  @NotNull(message = "imageList not null")
  private String imageList;

  @NotNull(message = "quantity not null")
  private Integer quantity;

  @NotNull(message = "description not null")
  private String description;

}
