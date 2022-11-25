package com.ecommerce.app.controllers.business;

import com.ecommerce.app.dtos.request.impl.ProductFilterDto;
import com.ecommerce.app.dtos.responses.ProductResponse;
import com.ecommerce.domain.entities.business.Product;
import com.ecommerce.domain.services.impl.business.ProductService;
import com.ecommerce.app.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductController extends BaseController<Product, Long, ProductResponse, ProductFilterDto> {

	@Autowired
  ProductService productService;

    public ProductController() {
        super(ProductResponse.class, ProductFilterDto.class);
    }

}
