package com.ecommerce.domain.repositories;

import com.ecommerce.domain.entities.business.Discount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

//  @Query("SELECT d FROM Discount d WHERE " +
//          "(:#{#req.productId} is null or d.product.id = :#{#req.productId})" +
//          "ORDER BY d.created_at DESC ")
//  Page<Discount> findAllByFilter(FilterDto<Discount> req, Pageable pageable);

  Discount findDiscountById(Long id);

  List<Discount> findDiscountsByIdIn(List<Long> ids);
}
