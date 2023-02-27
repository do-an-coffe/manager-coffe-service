package com.example.coffeebe.domain.repositories;

import com.example.coffeebe.domain.entities.business.ProductResourceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductResourceHistoryRepository extends JpaRepository<ProductResourceHistory, Long> {
}
