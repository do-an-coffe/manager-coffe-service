package com.ecommerce.domain.services.base;

import com.ecommerce.domain.ModelMapper;
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
}
