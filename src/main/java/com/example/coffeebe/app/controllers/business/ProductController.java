package com.example.coffeebe.app.controllers.business;

import com.example.coffeebe.app.controllers.BaseController;
import com.example.coffeebe.app.dtos.request.impl.ProductFilterDto;
import com.example.coffeebe.app.dtos.responses.CustomPage;
import com.example.coffeebe.app.dtos.responses.ProductResourceHistoryResponse;
import com.example.coffeebe.app.dtos.responses.ProductResponse;
import com.example.coffeebe.app.dtos.responses.ProductShortResponse;
import com.example.coffeebe.domain.entities.business.Product;
import com.example.coffeebe.domain.services.impl.business.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController extends BaseController<Product, Long, ProductResponse, ProductFilterDto> {

	@Autowired ProductService productService;

  public ProductController() {
        super(ProductResponse.class, ProductFilterDto.class);
    }

  @GetMapping("list")
  public List<ProductShortResponse> getListProduct(){
    return productService.getListProduct();
  }
}
