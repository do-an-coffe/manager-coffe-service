package com.ecommerce.app.controllers.business;

import com.ecommerce.app.dtos.impl.DiscountFilterDto;
import com.ecommerce.domain.entities.business.Discount;
import com.ecommerce.domain.services.impl.business.DiscountService;
import com.ecommerce.app.controllers.BaseController;
import com.ecommerce.app.responses.DiscountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/discount")
public class DiscountController extends BaseController<Discount, Long, DiscountResponse, DiscountFilterDto> {

    @Autowired
    DiscountService discountService;

    protected DiscountController() {
        super(DiscountResponse.class, DiscountFilterDto.class);
    }

}
