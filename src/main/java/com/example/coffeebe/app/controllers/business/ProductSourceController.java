package com.example.coffeebe.app.controllers.business;

import com.example.coffeebe.app.controllers.BaseController;
import com.example.coffeebe.app.dtos.request.impl.ProductSourceFilterDto;
import com.example.coffeebe.app.dtos.request.impl.ProductSourceStateDto;
import com.example.coffeebe.app.dtos.responses.CustomPage;
import com.example.coffeebe.app.dtos.responses.ProductResourceHistoryResponse;
import com.example.coffeebe.app.dtos.responses.ProductSourceResponse;
import com.example.coffeebe.domain.entities.business.ProductResourceHistory;
import com.example.coffeebe.domain.entities.business.ProductSource;
import com.example.coffeebe.domain.services.impl.business.ProductSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/product-source")
public class ProductSourceController extends BaseController<ProductSource, Long, ProductSourceResponse, ProductSourceFilterDto> {
  @Autowired
  ProductSourceService productSourceService;

  public ProductSourceController() {
    super(ProductSourceResponse.class, ProductSourceFilterDto.class);
  }

//  @PutMapping("{id}/state")
//  public Boolean changeStateProductSource(@PathVariable Long id, @Valid @RequestBody ProductSourceStateDto stateDto){
//    return productSourceService.changeStateProductSource(id, stateDto);
//  }

//  @GetMapping("histories")
//  public CustomPage<ProductResourceHistoryResponse> getHistory(Pageable pageable){
//    return productSourceService.getHistory(pageable);
//  }

  @GetMapping("histories")
  public List<ProductResourceHistoryResponse> getHistory(){
    return productSourceService.getHistory();
  }
}
