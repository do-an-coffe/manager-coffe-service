package com.ecommerce.domain.repositories;

import com.ecommerce.domain.entities.business.Product;
import com.ecommerce.app.dtos.request.FilterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByName(String name);

    @Query("SELECT p FROM Product p WHERE " +
            "(:#{#req.categoryId} is null or  p.category.id = :#{#req.categoryId}) " +
            "and " +
            "(:#{#req.categoryName} is null or  p.category.name = :#{#req.categoryName}) " +
            "and " +
            "(:#{#req.productName} is null or p.name like %:#{#req.productName}%) " )
    Page<Product> findAllByFilter(FilterDto<Product> req, Pageable pageable);

}
