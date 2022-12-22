package com.ecommerce.app.controllers.business;

import com.ecommerce.app.dtos.DiscountDto;
import com.ecommerce.app.responses.base.PageResponse;
import com.ecommerce.domain.entities.business.Discount;
import com.ecommerce.domain.services.impl.business.DiscountService;
import com.ecommerce.app.responses.DiscountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/discount")
public class DiscountController{

  @Autowired private DiscountService discountService;

  @GetMapping("all")
  public PageResponse<DiscountResponse> getAll(Pageable pageable){
    return PageResponse.createFrom(discountService.getAll(pageable));
  }

  @GetMapping("filter")
  public PageResponse<DiscountResponse> filter(Pageable pageable){
    return PageResponse.createFrom(discountService.getAll(pageable));
  }

  @GetMapping("{id}")
  public DiscountResponse detail(@PathVariable Long id){
    return discountService.detail(id);
  }

  @PostMapping()
  public Discount create(@RequestBody @Valid DiscountDto discountDto){
    return discountService.create(discountDto);
  }

  @PatchMapping("{id}")
  public Discount update(@PathVariable Long id, @RequestBody @Valid DiscountDto discountDto){
    return discountService.update(id, discountDto);
  }

  @DeleteMapping("{id}")
  public boolean delete(@PathVariable Long id){
    return discountService.delete(id);
  }
}
