package com.ecommerce.domain.services.impl.business;

import com.ecommerce.app.dtos.ProductDto;
import com.ecommerce.app.responses.ProductResponse;
import com.ecommerce.domain.entities.business.Category;
import com.ecommerce.domain.entities.business.Product;
import com.ecommerce.domain.exceptions.ResourceNotFoundException;
import com.ecommerce.domain.services.base.BaseService;
import com.ecommerce.domain.utils.exception.CustomErrorMessage;
import com.ecommerce.domain.utils.exception.CustomException;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ProductService extends BaseService {
  public Page<ProductResponse> getAll(Pageable pageable){
    Page<Product> productPage = productStorage.findAll(pageable);
    return mapperUtil.mapEntityPageIntoDtoPage(productPage, ProductResponse.class);
  }

  public Product findById(Long id) {
    Product product = productStorage.findProductsById(id);
    //todo: xu ly cache
    return product;
  }

  public ProductResponse detail(Long id){
    Product product = productStorage.findProductsById(id);
    if(product == null){
      throw new ResourceNotFoundException("Not found product id: " + id);
    }
    return modelMapper.toProductResponse(product);
  }

  public Product create(ProductDto dto) {
    if (productStorage.existsByName(dto.getName())){
      throw new CustomException(HttpStatus.BAD_REQUEST, CustomErrorMessage.NAME_EXISTS);
    }
    Category category = categoryStorage.findCategoryById(dto.getCategoryId());
    if(category == null){
      throw new ResourceNotFoundException("Not found category with id: " + dto.getCategoryId());
    }
    Product product = Product.builder()
            .category(category)
            .detail(dto.getDetail())
            .name(dto.getName())
            .image(dto.getImage())
            .imageList(dto.getImageList())
            .price(dto.getPrice())
            .quantity(dto.getQuantity())
            .description(dto.getDescription())
            .detail(dto.getDetail())
            .status(true)
            .build();
    return productStorage.save(product);
  }
  public Product update(Long id, ProductDto dto) {
    Product product = findById(id);
    if(product == null){
      throw new ResourceNotFoundException("Not found product with id: " + id);
    }

    Category category = categoryStorage.findCategoryById(dto.getCategoryId());
    if(category == null){
      throw new ResourceNotFoundException("Not found category with id: " + dto.getCategoryId());
    }

    product.setName(dto.getName());
    product.setCategory(category);
    product.setDetail(dto.getDetail());
    product.setImage(dto.getImage());
    product.setImageList(dto.getImageList());
    product.setPrice(dto.getPrice());
    product.setQuantity(dto.getQuantity());
    return productStorage.save(product);
  }

  public boolean delete(Long id) {
    Product product = findById(id);
    if(product == null){
      throw new ResourceNotFoundException("Not found product with id: " + id);
    }

    product.setStatus(false);
    productStorage.save(product);
    return true;
  }
}