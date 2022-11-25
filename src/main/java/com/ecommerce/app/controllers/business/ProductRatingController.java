package com.ecommerce.app.controllers.business;

import com.ecommerce.app.dtos.request.impl.ProductRatingFilterDto;
import com.ecommerce.app.dtos.responses.ProductRatingResponse;
import com.ecommerce.app.dtos.responses.ProductRatingResponseList;
import com.ecommerce.domain.entities.business.ProductRating;
import com.ecommerce.domain.services.impl.business.ProductRatingService;
import com.ecommerce.app.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/rating")
public class ProductRatingController extends BaseController<ProductRating, Long, ProductRatingResponse, ProductRatingFilterDto> {

    @Autowired
    ProductRatingService productRatingService;

    protected ProductRatingController() {
        super(ProductRatingResponse.class, ProductRatingFilterDto.class);
    }

    @GetMapping()
    List<ProductRatingResponseList> getAllByProduct(@RequestParam Long productId){
        return productRatingService.getListCmtByProductId(productId);
    }
}
