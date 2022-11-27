package com.ecommerce.domain.storage;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class CategoryStorage extends BaseStorage{

  public boolean existsByName(String name){
    return categoryRepository.existsByName(name);
  }
}
