package com.ecommerce.domain.repositories;

import com.ecommerce.domain.entities.business.Slider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SliderRepository extends JpaRepository<Slider,Long> {
}
