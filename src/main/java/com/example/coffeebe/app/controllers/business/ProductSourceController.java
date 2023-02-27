package com.example.coffeebe.app.controllers.business;

import com.example.coffeebe.app.controllers.BaseController;
import com.example.coffeebe.app.dtos.request.impl.ProductSourceFilterDto;
import com.example.coffeebe.app.dtos.request.impl.ProductSourceStateDto;
import com.example.coffeebe.app.dtos.responses.ProductSourceResponse;
import com.example.coffeebe.domain.entities.business.ProductSource;
import com.example.coffeebe.domain.services.impl.business.ProductSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/product-source")
public class ProductSourceController extends BaseController<ProductSource, Long, ProductSourceResponse, ProductSourceFilterDto> {
  @Autowired
  ProductSourceService productSourceService;

  public ProductSourceController() {
    super(ProductSourceResponse.class, ProductSourceFilterDto.class);
  }

  @PutMapping("/{id}/state")
  public Boolean changeStateProductSource(@PathVariable Long id, @Valid @RequestBody ProductSourceStateDto stateDto){
    return productSourceService.changeStateProductSource(id, stateDto);
  }
}
