package com.example.coffeebe.domain.services.impl.business;

import com.example.coffeebe.app.dtos.request.DTO;
import com.example.coffeebe.app.dtos.request.FilterDto;
import com.example.coffeebe.app.dtos.request.impl.ProductSourceDto;
import com.example.coffeebe.app.dtos.request.impl.ProductSourceStateDto;
import com.example.coffeebe.app.dtos.responses.CustomPage;
import com.example.coffeebe.app.dtos.responses.ProductResourceHistoryResponse;
import com.example.coffeebe.app.dtos.responses.ProductShortResponse;
import com.example.coffeebe.domain.entities.business.Product;
import com.example.coffeebe.domain.entities.business.ProductResourceHistory;
import com.example.coffeebe.domain.entities.business.ProductSource;
import com.example.coffeebe.domain.entities.enums.ProductResourceState;
import com.example.coffeebe.domain.services.BaseService;
import com.example.coffeebe.domain.services.impl.BaseAbtractService;
import com.example.coffeebe.domain.utils.LockManager;
import com.example.coffeebe.domain.utils.exception.CustomErrorMessage;
import com.example.coffeebe.domain.utils.exception.CustomException;
import lombok.extern.log4j.Log4j2;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class ProductSourceService extends BaseAbtractService implements BaseService<ProductSource, Long> {

  @Autowired
  private LockManager lockManager;

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
    ProductSource productSource = ProductSource.builder()
            .product(product)
            .quantity(productSourceDto.getQuantity())
            .price(productSourceDto.getPrice())
            .totalPrice(productSourceDto.getPrice() * productSourceDto.getQuantity())
            .state(ProductResourceState.NEW)
            .status(true).build();
    return productSourceRepository.save(productSource);
  }

  @Override
  public ProductSource update(HttpServletRequest request, Long id, DTO dto) {
    ProductSourceDto productSourceDto = modelMapper.map(dto, ProductSourceDto.class);
    Product product = getProductById(productSourceDto.getProductId());
    ProductSource productSource = getProductSourceById(id);
    productSource.setProduct(product);
    productSource.setPrice(productSourceDto.getPrice());
    productSource.setQuantity(productSourceDto.getQuantity());
    productSource.setTotalPrice(productSourceDto.getPrice() * productSourceDto.getQuantity());
    return productSourceRepository.save(productSource);
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

  @Transactional(
          propagation = Propagation.REQUIRES_NEW,
          isolation = Isolation.SERIALIZABLE,
          rollbackFor = {CustomException.class})
  public Boolean changeStateProductSource(Long id, ProductSourceStateDto stateDto){
    ProductSource productSource = getProductSourceById(id);
    if(productSource.getState() != ProductResourceState.NEW){
      throw new CustomException(HttpStatus.BAD_REQUEST, CustomErrorMessage.PRODUCT_RESOURCE_STATE_ERROR);
    }
    productSource.setState(stateDto.getState());
    productSourceRepository.save(productSource);

    processUpdateProduct(productSource.getProduct(), productSource);
    return true;
  }

  @Transactional(propagation = Propagation.MANDATORY)
  public void processUpdateProduct(Product product, ProductSource productSource){
    if(productSource.getState() == ProductResourceState.ACTIVE){
      int preQuantity;
      int currentQuantity;
      preQuantity = product.getQuantity();
      currentQuantity = preQuantity + productSource.getQuantity();

      product.setQuantity(currentQuantity);
      productRepository.save(product);

      ProductResourceHistory history = ProductResourceHistory.builder().product(product).productSource(productSource)
              .preQuantity(preQuantity)
              .currentQuantity(currentQuantity).build();
      productResourceHistoryRepository.save(history);
    }
  }

//  public CustomPage<ProductResourceHistoryResponse> getHistory(Pageable pageable){
//    Page<ProductResourceHistory> history =  productResourceHistoryRepository.findAll(pageable);
//    CustomPage<ProductResourceHistory> page = new CustomPage<>(history);
//    CustomPage<ProductResourceHistoryResponse> customPage = new CustomPage<>();
//
//    customPage.setData(page.getData().stream().map(ele -> modelMapper.map(ele, ProductResourceHistoryResponse.class)).collect(Collectors.toList()));
//    customPage.setMetadata(new CustomPage.Metadata(page.getMetadata().getPage(), page.getMetadata().getSize(),
//            page.getMetadata().getTotal(), page.getMetadata().getTotalPage()));
//    return customPage;
//  }

  public List<ProductResourceHistoryResponse> getHistory(){
    List<ProductResourceHistory> historyList =  productResourceHistoryRepository.findAll();
    return historyList.stream().map(history -> modelMapper.map(history, ProductResourceHistoryResponse.class)).collect(Collectors.toList());
  }
}
