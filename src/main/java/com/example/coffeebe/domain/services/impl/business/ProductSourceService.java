package com.example.coffeebe.domain.services.impl.business;

import com.example.coffeebe.app.dtos.request.DTO;
import com.example.coffeebe.app.dtos.request.FilterDto;
import com.example.coffeebe.app.dtos.request.impl.ProductSourceDto;
import com.example.coffeebe.app.dtos.responses.CustomPage;
import com.example.coffeebe.domain.entities.business.Product;
import com.example.coffeebe.domain.entities.business.ProductSource;
import com.example.coffeebe.domain.services.BaseService;
import com.example.coffeebe.domain.services.impl.BaseAbtractService;
import com.example.coffeebe.domain.utils.exception.CustomErrorMessage;
import com.example.coffeebe.domain.utils.exception.CustomException;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@Log4j2
public class ProductSourceService extends BaseAbtractService implements BaseService<ProductSource, Long> {
  @Override
  public CustomPage<ProductSource> findAll(Pageable pageable) {
    Page<ProductSource> productSources =  productSourceRepository.findAll(pageable);
    return new CustomPage<>(productSources);
  }

  @Override
  public ProductSource findById(HttpServletRequest request, Long id) {
    return getProductSourceById(id);
  }

  @Override
  public ProductSource create(HttpServletRequest request, DTO dto) {
    ProductSourceDto productSourceDto = modelMapper.map(dto, ProductSourceDto.class);
    Product product = getProductById(productSourceDto.getProductId());
    ProductSource productSource = ProductSource.builder().product(product).quantity(productSourceDto.getQuantity()).status(false).build();
    return productSourceRepository.save(productSource);
  }

  @Override
  public ProductSource update(HttpServletRequest request, Long id, DTO dto) {
    return null;
  }

  @Override
  public boolean delete(HttpServletRequest request, Long id) {
    ProductSource productSource = findById(request, id);
    productSource.setStatus(false);
    productSourceRepository.save(productSource);
    return false;
  }

  @Override
  public Page<ProductSource> findAllByFilter(FilterDto<ProductSource> dto, Pageable pageable) {
    return null;
  }

  @Override
  public List<ProductSource> findAllByFilter(HttpServletRequest request) {
    return null;
  }
}
