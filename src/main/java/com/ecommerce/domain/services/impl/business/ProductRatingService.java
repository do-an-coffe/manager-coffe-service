package com.ecommerce.domain.services.impl.business;

import com.ecommerce.app.dtos.impl.ProductRatingDto;
import com.ecommerce.app.responses.ProductRatingResponse;
import com.ecommerce.app.responses.ProductRatingResponseList;
import com.ecommerce.domain.entities.business.Product;
import com.ecommerce.domain.entities.business.ProductRating;
import com.ecommerce.domain.services.base.BaseService;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class ProductRatingService extends BaseService {
  public Page<ProductRatingResponse> getAll(Pageable pageable){
    Page<ProductRating> productRatings = productRatingStorage.findAll(pageable);
    return mapperUtil.mapEntityPageIntoDtoPage(productRatings, ProductRatingResponse.class);
  }

  public ProductRatingResponse detail(Long id){
    return modelMapper.toProductRatingResponse(findProductRatingById(id));
  }

  public ProductRating create(String userEmail, ProductRatingDto dto) {
    ProductRating productRating = ProductRating.builder()
            .product(findProductById(dto.getProductId()))
            .user(findUserByEmail(userEmail))
            .content(dto.getContent())
            .numberStar(dto.getNumberStar())
            .build();

    return productRatingStorage.save(productRating);
  }

  public ProductRating update(Long id, ProductRatingDto dto) {
    ProductRating productRating = findProductRatingById(id);
    productRating.setContent(dto.getContent());
    productRating.setNumberStar(dto.getNumberStar());

    return productRatingStorage.save(productRating);
  }

  public boolean delete(Long id) {
    ProductRating productRating = findProductRatingById(id);
    productRatingStorage.delete(productRating);
    return true;
  }

  public List<ProductRatingResponseList> getListCmtByProductId(Long id){
    List<ProductRatingResponseList> responseLists = new ArrayList<>();
    Product product = findProductById(id);
    if (product != null) {
      List<ProductRating> productRatings = productRatingStorage.findAllByProduct(product);
      for (ProductRating p: productRatings
      ) {
        ProductRatingResponseList productRatingResponseList = new ProductRatingResponseList();
        productRatingResponseList.setName(p.getUser().getFullName());
        productRatingResponseList.setTime(p.getCreated_at());
        productRatingResponseList.setContent(p.getContent());
        productRatingResponseList.setNumber_star(p.getNumberStar());
        responseLists.add(productRatingResponseList);
      }
      return responseLists;
    }
    return null;
  }
}
