package com.ecommerce.app.controllers.business;

import com.ecommerce.app.dtos.DiscountDto;
import com.ecommerce.app.dtos.OrderDto;
import com.ecommerce.app.dtos.impl.OrderFilterDto;
import com.ecommerce.app.responses.DiscountResponse;
import com.ecommerce.app.responses.OrderResponse;
import com.ecommerce.app.responses.base.PageResponse;
import com.ecommerce.domain.entities.business.Discount;
import com.ecommerce.domain.entities.business.Order;
import com.ecommerce.domain.services.impl.business.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/order1")
public class OrderController{

  @Autowired private OrderService orderService;

  @GetMapping("all")
  public PageResponse<OrderResponse> getAll(Pageable pageable){
    return PageResponse.createFrom(orderService.getAll(pageable));
  }

  @GetMapping("filter")
  public PageResponse<OrderResponse> filter(Pageable pageable){
    return PageResponse.createFrom(orderService.getAll(pageable));
  }

  @GetMapping("{id}")
  public OrderResponse detail(@PathVariable Long id){
    return orderService.detail(id);
  }

  @PostMapping()
  public Discount create(@RequestBody @Valid OrderDto discountDto){
    return orderService.create(discountDto);
  }

  @PatchMapping("{id}")
  public Discount update(@PathVariable Long id, @RequestBody @Valid OrderDto discountDto){
    return orderService.update(id, discountDto);
  }

  @DeleteMapping("{id}")
  public boolean delete(@PathVariable Long id){
    return orderService.delete(id);
  }
}
