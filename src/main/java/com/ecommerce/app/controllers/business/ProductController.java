package com.ecommerce.app.controllers.business;

import com.ecommerce.app.dtos.ProductDto;
import com.ecommerce.app.responses.ProductResponse;
import com.ecommerce.app.responses.base.PageResponse;
import com.ecommerce.domain.entities.business.Product;
import com.ecommerce.domain.services.impl.business.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired private ProductService productService;

  @GetMapping("all")
  public PageResponse<ProductResponse> getAll(Pageable pageable){
    return PageResponse.createFrom(productService.getAll(pageable));
  }

  @GetMapping("filter")
  public PageResponse<ProductResponse> filter(Pageable pageable){
    return PageResponse.createFrom(productService.getAll(pageable));
  }

  @GetMapping("{id}")
  public ProductResponse detail(@PathVariable Long id){
    return productService.detail(id);
  }

  @PostMapping()
  public Product create(@RequestBody @Valid ProductDto productDto){
    return productService.create(productDto);
  }

  @PatchMapping("{id}")
  public Product update(@PathVariable Long id, @RequestBody @Valid ProductDto productDto){
    return productService.update(id, productDto);
  }

  @DeleteMapping("{id}")
  public boolean delete(@PathVariable Long id){
    return productService.delete(id);
  }

}
