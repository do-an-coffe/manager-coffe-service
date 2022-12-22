package com.ecommerce.app.dtos;

import com.ecommerce.app.dtos.DTO;
import com.ecommerce.domain.entities.business.Discount;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class DiscountDto{

  private Long id;

  @NotNull(message = "productId not null")
  @JsonProperty("product_id")
  private Long productId;

  @NotNull(message = "startDate not null")
  private Date startDate;

  @NotNull(message = "endDate not null")
  private Date endDate;

  @NotNull(message = "discount not null")
  private Integer discount;

  private String name;

}
