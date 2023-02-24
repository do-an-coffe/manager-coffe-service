package com.example.coffeebe.domain.repositories;

import com.example.coffeebe.domain.entities.business.ProductSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSourceRepository extends JpaRepository<ProductSource, Long> {
}
