package com.ecommerce.domain.repositories;

import com.ecommerce.domain.entities.business.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

//    @Query("select c from Category c order by c.parentId asc")
//    List<Category> getAllCategory();


  boolean existsByName(String name);

  Category findCategoryById(Long id);
}
