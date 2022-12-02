package com.ecommerce.domain.storage;

import com.ecommerce.domain.entities.business.Category;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class CategoryStorage extends BaseStorage{

  public boolean existsByName(String name){
    return categoryRepository.existsByName(name);
  }

  public Category save(Category category){
    return categoryRepository.save(category);
  }

  public Page<Category> findAll(Pageable pageable){
    return categoryRepository.findAll(pageable);
  }

  public Category findCategoryById(Long id){
    return categoryRepository.findCategoryById(id);
  }

}
