package com.ecommerce.domain.services.base;

import com.ecommerce.domain.ModelMapper;
import com.ecommerce.domain.entities.author.User;
import com.ecommerce.domain.entities.business.Product;
import com.ecommerce.domain.entities.business.ProductRating;
import com.ecommerce.domain.entities.business.Slider;
import com.ecommerce.domain.entities.business.Transaction;
import com.ecommerce.domain.exceptions.ResourceNotFoundException;
import com.ecommerce.domain.storage.*;
import com.ecommerce.domain.utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseService {
  @Autowired protected CategoryStorage categoryStorage;
  @Autowired protected DiscountStorage discountStorage;
  @Autowired protected OrderStorage orderStorage;
  @Autowired protected ProductRatingStorage productRatingStorage;
  @Autowired protected ProductStorage productStorage;
  @Autowired protected RoleStorage roleStorage;
  @Autowired protected SliderStorage sliderStorage;
  @Autowired protected TransactionStorage transactionStorage;
  @Autowired protected UserStorage userStorage;
  @Autowired protected ModelMapper modelMapper;
  @Autowired protected MapperUtil mapperUtil;

  public User findUserByEmail(String email) {
    User user = userStorage.findByEmail(email);
    if(user == null){
      throw new ResourceNotFoundException("Not found user email : " + email);
    }
    //todo: xu ly cache
    return user;
  }

  public Product findProductById(Long id) {
    Product product = productStorage.findProductsById(id);
    if(product == null){
      throw new ResourceNotFoundException("Not found product id: " + id);
    }
    //todo: xu ly cache
    return product;
  }

  public Slider findSliderById(Long id) {
    Slider slider = sliderStorage.findSliderById(id);
    if(slider == null){
      throw new ResourceNotFoundException("Not found slider id: " + id);
    }
    //todo: xu ly cache
    return slider;
  }

  public ProductRating findProductRatingById(Long id) {
    ProductRating productRating = productRatingStorage.findProductRatingById(id);
    if(productRating == null){
      throw new ResourceNotFoundException("Not found productRating id: " + id);
    }
    //todo: xu ly cache
    return productRating;
  }

  public Transaction findTransactionById(Long id) {
    Transaction transaction = transactionStorage.findTransactionById(id);
    if(transaction == null){
      throw new ResourceNotFoundException("Not found transaction id: " + id);
    }
    //todo: xu ly cache
    return transaction;
  }
}
