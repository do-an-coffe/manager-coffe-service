package com.ecommerce.app.controllers.business;

import com.ecommerce.app.dtos.CategoryDto;
import com.ecommerce.app.dtos.impl.CategoryFilterDto;
import com.ecommerce.app.responses.base.PageResponse;
import com.ecommerce.domain.entities.business.Category;
import com.ecommerce.domain.services.impl.business.CategoryService;
import com.ecommerce.app.controllers.BaseController;
import com.ecommerce.app.responses.CategoryResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/category")
public class CategoryController {

	@Autowired private CategoryService categoryService;

  @GetMapping("all")
  public PageResponse<CategoryResponse> getAll(Pageable pageable){
    return PageResponse.createFrom(categoryService.getAll(pageable));
  }

  @GetMapping("filter")
  public PageResponse<CategoryResponse> filter(Pageable pageable){
    return PageResponse.createFrom(categoryService.getAll(pageable));
  }

  @GetMapping("{id}")
  public CategoryResponse detail(@PathVariable Long id){
    return categoryService.detail(id);
  }

  @PostMapping()
  public Category create(CategoryDto categoryDto){
    return categoryService.create(categoryDto);
  }

  @PatchMapping("{id}")
  public Category update(@PathVariable Long id, CategoryDto categoryDto){
    return categoryService.update(id, categoryDto);
  }

  @DeleteMapping("{id}")
  public boolean delete(@PathVariable Long id){
    return categoryService.delete(id);
  }
}
