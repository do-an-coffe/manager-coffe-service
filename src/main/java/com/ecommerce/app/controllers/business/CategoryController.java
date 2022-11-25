package com.ecommerce.app.controllers.business;

import com.ecommerce.app.dtos.request.impl.CategoryFilterDto;
import com.ecommerce.domain.entities.business.Category;
import com.ecommerce.domain.services.impl.business.CategoryService;
import com.ecommerce.app.controllers.BaseController;
import com.ecommerce.app.dtos.responses.CategoryResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/category")
public class CategoryController extends BaseController<Category, Long, CategoryResponse, CategoryFilterDto> {

	@Autowired
  CategoryService categoryService;

    public CategoryController() {
        super(CategoryResponse.class, CategoryFilterDto.class);
    }

//    @GetMapping("/tree")
//    public List<CategoryResponse> initRole(){
//        return categoryService.getCategoryTree();
//    }

}
