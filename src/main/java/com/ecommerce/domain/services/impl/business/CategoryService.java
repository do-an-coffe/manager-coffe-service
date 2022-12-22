package com.ecommerce.domain.services.impl.business;

import com.ecommerce.app.dtos.CategoryDto;
import com.ecommerce.app.responses.CategoryResponse;
import com.ecommerce.domain.entities.business.Category;
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
public class CategoryService extends BaseService {

  public Page<CategoryResponse> getAll(Pageable pageable){
    Page<Category> categoryPage = categoryStorage.findAll(pageable);
    return mapperUtil.mapEntityPageIntoDtoPage(categoryPage, CategoryResponse.class);
  }

  public CategoryResponse detail(Long id){
    Category category = categoryStorage.findCategoryById(id);
    if(category == null){
      throw new ResourceNotFoundException("Not found category id: " + id);
    }
    return modelMapper.toCategoryResponse(category);
  }

  public Category create(CategoryDto categoryDto){
    if (categoryStorage.existsByName(categoryDto.getName())){
      throw new CustomException(HttpStatus.BAD_REQUEST, CustomErrorMessage.NAME_EXISTS);
    }
    Category category = Category.builder()
            .name(categoryDto.getName())
            .position(categoryDto.getPosition())
            .productDetail(categoryDto.getProductDetail())
            .status(true)
            .build();
    return categoryStorage.save(category);
  }

  public Category update(Long id, CategoryDto categoryDto){
    Category category = categoryStorage.findCategoryById(id);
    if(category == null){
      throw new ResourceNotFoundException("Not found category id: " + id);
    }

    if (categoryStorage.existsByName(categoryDto.getName())){
      throw new CustomException(HttpStatus.BAD_REQUEST, CustomErrorMessage.NAME_EXISTS);
    }
    category.setName(categoryDto.getName());
    category.setPosition(categoryDto.getPosition());
    category.setProductDetail(categoryDto.getProductDetail());
    return categoryStorage.save(category);
  }

  public boolean delete(Long id){
    Category category = categoryStorage.findCategoryById(id);
    if(category == null){
      throw new ResourceNotFoundException("Not found category id: " + id);
    }

    category.setStatus(false);
    categoryStorage.save(category);
    return true;
  }

}