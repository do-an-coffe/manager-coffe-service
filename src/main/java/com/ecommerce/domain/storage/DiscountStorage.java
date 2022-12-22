package com.ecommerce.domain.storage;

import com.ecommerce.domain.entities.business.Category;
import com.ecommerce.domain.entities.business.Discount;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class DiscountStorage extends BaseStorage{
  public Discount save(Discount discount){
    return discountRepository.save(discount);
  }

  public Page<Discount> findAll(Pageable pageable){
    return discountRepository.findAll(pageable);
  }

  public Discount findDiscountById(Long id){
    return discountRepository.findDiscountById(id);
  }

  public void delete(Discount discount){
    discountRepository.delete(discount);
  }
}
