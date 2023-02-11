package com.ecommerce.domain.services.impl.business;

import com.ecommerce.app.dtos.OrderDto;
import com.ecommerce.app.responses.OrderResponse;
import com.ecommerce.domain.entities.business.Order;
import com.ecommerce.domain.exceptions.ResourceNotFoundException;
import com.ecommerce.domain.services.base.BaseService;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class OrderService extends BaseService {

  public Page<OrderResponse> getAll(Pageable pageable) {
    Page<Order> orderPage = orderStorage.findAll(pageable);
    return mapperUtil.mapEntityPageIntoDtoPage(orderPage, OrderResponse.class);
  }

  public OrderResponse detail(Long id){
    return modelMapper.toOrderResponse(findById(id));
  }

  public Order findById(Long id) {
    Order order = orderStorage.findOrderById(id);
    if(order == null){
      throw new ResourceNotFoundException("Not found order with id: " + id);
    }
    return order;
  }

  public Order create(OrderDto orderDto) {
    Order order = Order.builder()
            .quantity(orderDto.getQuantity())
//                .amount(orderDto.getAmount())
//                .data(orderDto.getData())
//                .status(orderDto.getStatus())
                .product(findProductById(orderDto.getProductID()))
//                .transaction(getTransactionById(orderDto.getTransactionId()))
            .build();

    return orderStorage.save(order);
  }

  public Order update(Long id, OrderDto dto) {
    Order order = findById(id);
    order.setQuantity(dto.getQuantity());
//        order.setAmount(orderDto.getAmount());
//        order.setData(orderDto.getData());
//        order.setStatus(orderDto.getStatus());
//    order.setProduct(getProductById(orderDto.getProductID()));
    //   order.setTransaction(getTransactionById(orderDto.getTransactionId()));

    return orderStorage.save(order);  }

  public boolean delete(Long id) {
    Order order = findById(id);
    orderStorage.delete(order);
    return true;
  }
}
