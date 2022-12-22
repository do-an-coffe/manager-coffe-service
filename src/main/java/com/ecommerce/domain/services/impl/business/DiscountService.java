package com.ecommerce.domain.services.impl.business;

import com.ecommerce.app.dtos.DTO;
import com.ecommerce.app.dtos.DiscountDto;
import com.ecommerce.app.responses.CategoryResponse;
import com.ecommerce.app.responses.CustomPage;
import com.ecommerce.app.responses.DiscountResponse;
import com.ecommerce.domain.entities.business.Discount;
import com.ecommerce.domain.entities.business.Product;
import com.ecommerce.domain.exceptions.ResourceNotFoundException;
import com.ecommerce.domain.services.base.BaseService;
import com.ecommerce.domain.services.impl.BaseAbtractService;
import com.ecommerce.domain.utils.exception.CustomErrorMessage;
import com.ecommerce.domain.utils.exception.CustomException;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@Log4j2
public class DiscountService extends BaseService {
  public Page<DiscountResponse> getAll(Pageable pageable) {
    Page<Discount> discountPage = discountStorage.findAll(pageable);
    return mapperUtil.mapEntityPageIntoDtoPage(discountPage, DiscountResponse.class);
  }


  public Discount findById(Long id) {
    Discount discount = discountStorage.findDiscountById(id);
    //todo: cache
    return discount;
  }

  public DiscountResponse detail(Long id){
    Discount discount = discountStorage.findDiscountById(id);
    if(discount == null){
      throw new ResourceNotFoundException("Not found discount with id: " + id);
    }
    return modelMapper.toDiscountResponse(discount);
  }
  public Discount create(DiscountDto dto) {

    Product product = productStorage.findProductsById(dto.getProductId());
    if(product == null){
      throw new ResourceNotFoundException("Not found product id: " + dto.getProductId());
    }
    List<Discount> discounts = product.getDiscounts();

    if (dto.getStartDate().getTime() > dto.getEndDate().getTime()){
      throw new CustomException(HttpStatus.BAD_REQUEST, CustomErrorMessage.TIME_INVALID);
    }

    if (discounts.parallelStream().anyMatch(item -> (dto.getStartDate().getTime() < item.getEndDate().getTime() && dto.getEndDate().getTime() >= item.getStartDate().getTime())))
    {
      throw new CustomException(HttpStatus.BAD_REQUEST, CustomErrorMessage.DUPLICATE_TIME_RECORD);
    }

    Discount discount = Discount.builder()
            .product(product)
            .startDate(dto.getStartDate())
            .endDate(dto.getEndDate())
            .discount(dto.getDiscount())
            .name(dto.getName())
            .build();

    return discountStorage.save(discount);
  }

  public Discount update(Long id, DiscountDto dto) {
    Discount discount = discountStorage.findDiscountById(id);
    if(discount == null){
      throw new ResourceNotFoundException("Not found discount with id: " + id);
    }

    Product product = productStorage.findProductsById(dto.getProductId());
    if(product == null){
      throw new ResourceNotFoundException("Not found product id: " + dto.getProductId());
    }

    discount.setProduct(product);
    discount.setStartDate(dto.getStartDate());
    discount.setEndDate(dto.getEndDate());
    discount.setDiscount(dto.getDiscount());
    discount.setName(dto.getName());

    return discountStorage.save(discount);
  }

  public boolean delete(Long id) {
    Discount discount = discountStorage.findDiscountById(id);
    if(discount == null){
      throw new ResourceNotFoundException("Not found discount with id: " + id);
    }
    discountStorage.delete(discount);
    return true;
  }


}
