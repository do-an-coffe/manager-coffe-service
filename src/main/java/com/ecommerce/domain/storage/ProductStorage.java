package com.ecommerce.domain.storage;

import com.ecommerce.domain.entities.business.Product;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class ProductStorage extends BaseStorage{
  public Page<Product> findAllByFilter(FilterDto<Product> req, Pageable pageable){
    return productRepository.findAllByFilter(req, pageable);
  }

  public boolean existsByName(String name){
    return productRepository.existsByName(name);
  }

  public Page<Product> findAll(Pageable pageable){
    return productRepository.findAll(pageable);
  }

  public Product findProductsById(Long id){
    return productRepository.findProductsById(id);
  }

  public Product save(Product product){
    return productRepository.save(product);
  }
}
