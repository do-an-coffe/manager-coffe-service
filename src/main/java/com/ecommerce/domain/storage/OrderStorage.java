package com.ecommerce.domain.storage;

import com.ecommerce.domain.entities.business.Discount;
import com.ecommerce.domain.entities.business.Order;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class OrderStorage extends BaseStorage{
  public Page<Order> findAll(Pageable pageable){
    return orderRepository.findAll(pageable);
  }

  public Order findOrdersById(Long id){
    return orderRepository.findOrdersById(id);
  }

  public Order save(Order order){
    return orderRepository.save(order);
  }

  public void delete(Order order){
    orderRepository.delete(order);
  }
}
