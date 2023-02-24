package com.example.coffeebe.app.controllers.business;

import com.example.coffeebe.app.controllers.BaseController;
import com.example.coffeebe.app.dtos.request.impl.ProductSourceFilterDto;
import com.example.coffeebe.app.dtos.responses.ProductSourceResponse;
import com.example.coffeebe.domain.entities.business.ProductSource;
import com.example.coffeebe.domain.services.impl.business.ProductSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/product-source")
public class ProductSourceController extends BaseController<ProductSource, Long, ProductSourceResponse, ProductSourceFilterDto> {
  @Autowired
  ProductSourceService productSourceService;

  public ProductSourceController() {
    super(ProductSourceResponse.class, ProductSourceFilterDto.class);
  }
}
