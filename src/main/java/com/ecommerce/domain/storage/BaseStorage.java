package com.ecommerce.domain.storage;

import com.ecommerce.domain.repositories.*;
import com.ecommerce.domain.utils.cache.CacheKey;
import com.ecommerce.domain.utils.cache.RemoteCache;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class BaseStorage {
  @Autowired
  public UserRepository userRepository;

  @Autowired
  public RoleRepository roleRepository;

  @Autowired
  public CategoryRepository categoryRepository;

  @Autowired
  public SliderRepository sliderRepository;

  @Autowired
  public ModelMapper modelMapper;

  @Autowired @Lazy
  protected RemoteCache remoteCache;
  @Autowired
  public ProductRepository productRepository;

  @Autowired
  public DiscountRepository discountRepository;

  @Autowired
  public OrderRepository orderRepository;

  @Autowired
  public TransactionRepository transactionRepository;

  @Autowired
  public ProductRatingRepository productRatingRepository;
}
