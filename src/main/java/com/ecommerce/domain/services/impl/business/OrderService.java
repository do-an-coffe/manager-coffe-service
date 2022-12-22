package com.ecommerce.domain.services.impl.business;

import com.ecommerce.app.dtos.OrderDto;
import com.ecommerce.app.dtos.DTO;
import com.ecommerce.app.responses.CustomPage;
import com.ecommerce.domain.entities.business.Order;
import com.ecommerce.domain.services.base.BaseService;
import com.ecommerce.domain.services.impl.BaseAbtractService;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@Log4j2
public class OrderService extends BaseService {
  public CustomPage<Order> findAll(Pageable pageable) {
    return null;
  }

  public Order findById(Long id) {
    Order order = orderStorage.
    return getOrderById(id);
  }

  public Order create(HttpServletRequest request, DTO dto) {
    OrderDto orderDto = modelMapper.map(dto, OrderDto.class);
    Order order = Order.builder()
            .quantity(orderDto.getQuantity())
            .build();

    return orderRepository.save(order);
  }

  public Order update(HttpServletRequest request, Long id, DTO dto) {
    Order order = findById(request, id);
    OrderDto orderDto = modelMapper.map(dto, OrderDto.class);
    order.setQuantity(orderDto.getQuantity());
//        order.setAmount(orderDto.getAmount());
//        order.setData(orderDto.getData());
//        order.setStatus(orderDto.getStatus());
    order.setProduct(getProductById(orderDto.getProductID()));
    //   order.setTransaction(getTransactionById(orderDto.getTransactionId()));

    return orderRepository.save(order);
  }

  public boolean delete(HttpServletRequest request, Long id) {
    Order order = findById(request, id);
    orderRepository.delete(order);
    return true;
  }


  public Page<Order> findAllByFilter(FilterDto<Order> dto, Pageable pageable) {
    return null;
  }


  public List<Order> findAllByFilter(HttpServletRequest request) {
    return null;
  }
}
