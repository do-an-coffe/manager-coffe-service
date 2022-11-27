package com.ecommerce.app.controllers.business;

import com.ecommerce.app.dtos.impl.OrderFilterDto;
import com.ecommerce.app.responses.OrderResponse;
import com.ecommerce.domain.entities.business.Order;
import com.ecommerce.domain.services.impl.business.OrderService;
import com.ecommerce.app.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order1")
public class OrderController extends BaseController<Order, Long, OrderResponse, OrderFilterDto> {

    @Autowired
    OrderService orderService;

    public OrderController(){
        super(OrderResponse.class, OrderFilterDto.class);
    }
}
