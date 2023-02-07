package com.ecommerce.app.controllers.business;

import com.ecommerce.app.dtos.CategoryDto;
import com.ecommerce.app.dtos.impl.ProductRatingDto;
import com.ecommerce.app.dtos.impl.ProductRatingFilterDto;
import com.ecommerce.app.responses.CategoryResponse;
import com.ecommerce.app.responses.ProductRatingResponse;
import com.ecommerce.app.responses.ProductRatingResponseList;
import com.ecommerce.app.responses.base.PageResponse;
import com.ecommerce.domain.entities.business.Category;
import com.ecommerce.domain.entities.business.ProductRating;
import com.ecommerce.domain.services.impl.business.ProductRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/rating")
public class ProductRatingController{

  @Autowired private ProductRatingService productRatingService;

  @GetMapping()
  List<ProductRatingResponseList> getAllByProduct(@RequestParam Long productId){
    return productRatingService.getListCmtByProductId(productId);
  }

  @GetMapping("all")
  public PageResponse<ProductRatingResponse> getAll(Pageable pageable){
    return PageResponse.createFrom(productRatingService.getAll(pageable));
  }

  @GetMapping("filter")
  public PageResponse<ProductRatingResponse> filter(Pageable pageable){
    return PageResponse.createFrom(productRatingService.getAll(pageable));
  }

  @GetMapping("{id}")
  public ProductRatingResponse detail(@PathVariable Long id){
    return productRatingService.detail(id);
  }

  @PostMapping()
  public ProductRating create(Authentication authentication, @RequestBody @Valid ProductRatingDto productRatingDto){
    String userEmail = authentication.getName();
    return productRatingService.create(userEmail, productRatingDto);
  }

  @PatchMapping("{id}")
  public ProductRating update(@PathVariable Long id, @RequestBody @Valid ProductRatingDto productRatingDto){
    return productRatingService.update(id, productRatingDto);
  }

  @DeleteMapping("{id}")
  public boolean delete(@PathVariable Long id){
    return productRatingService.delete(id);
  }
}
