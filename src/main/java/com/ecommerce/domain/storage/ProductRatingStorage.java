package com.ecommerce.domain.storage;

import com.ecommerce.domain.entities.business.Category;
import com.ecommerce.domain.entities.business.Product;
import com.ecommerce.domain.entities.business.ProductRating;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Log4j2
public class ProductRatingStorage extends BaseStorage{
  public Page<ProductRating> findAll(Pageable pageable){
    return productRatingRepository.findAll(pageable);
  }

  public ProductRating save(ProductRating productRating){
    return productRatingRepository.save(productRating);
  }

  public void delete(ProductRating productRating){
    productRatingRepository.delete(productRating);
  }

  public ProductRating findProductRatingById(Long id){
    return productRatingRepository.findProductRatingById(id);
  }

  public List<ProductRating> findAllByProduct(Product product){
    return productRatingRepository.findAllByProduct(product);
  }

}
