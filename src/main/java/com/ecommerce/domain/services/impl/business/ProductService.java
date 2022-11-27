package com.ecommerce.domain.services.impl.business;

import com.ecommerce.app.dtos.DTO;
import com.ecommerce.app.dtos.FilterDto;
import com.ecommerce.app.dtos.impl.ProductDto;
import com.ecommerce.app.dtos.impl.ProductFilterDto;
import com.ecommerce.app.responses.CustomPage;
import com.ecommerce.domain.entities.business.Category;
import com.ecommerce.domain.entities.business.Product;
import com.ecommerce.domain.services.BaseService;
import com.ecommerce.domain.services.impl.BaseAbtractService;
import com.ecommerce.domain.utils.exception.CustomErrorMessage;
import com.ecommerce.domain.utils.exception.CustomException;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@Log4j2
public class ProductService extends BaseAbtractService implements BaseService<Product, Long> {

    @Override
    public CustomPage<Product> findAll(Pageable pageable) {
        Page<Product> productPage =  productRepository.findAll(pageable);
        return new CustomPage<>(productPage);
    }

    @Override
    public Product findById(HttpServletRequest request, Long id) {
        return getProductById(id);
    }

    @Override
    public Product create(HttpServletRequest request, DTO dto) {
        ProductDto productDto = modelMapper.map(dto, ProductDto.class);
        if (productRepository.existsByName(productDto.getName())){
            throw new CustomException(HttpStatus.BAD_REQUEST, CustomErrorMessage.NAME_EXISTS);
        }
        Category category = getCategoryById(productDto.getCategoryId());
        Product product = Product.builder()
                .category(category)
                .detail(productDto.getDetail())
                .name(productDto.getName())
                .image(productDto.getImage())
                .imageList(productDto.getImageList())
                .price(productDto.getPrice())
                .quantity(productDto.getQuantity())
                .description(productDto.getDescription())
                .detail(productDto.getDetail())
                .status(true)
                .build();
        return productRepository.save(product);
    }

    @Override
    public Product update(HttpServletRequest request, Long id, DTO dto) {
        Product product = findById(request, id);
        ProductDto productDto = modelMapper.map(dto, ProductDto.class);
        Category category = getCategoryById(productDto.getCategoryId());
        product.setName(productDto.getName());
        product.setCategory(category);
        product.setDetail(productDto.getDetail());
        product.setImage(productDto.getImage());
        product.setImageList(productDto.getImageList());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        return productRepository.save(product);
    }

    @Override
    public boolean delete(HttpServletRequest request, Long id) {
        Product product = findById(request, id);
        product.setStatus(false);
        productRepository.save(product);
        return true;
    }

    @Override
    public Page<Product> findAllByFilter(FilterDto<Product> dto, Pageable pageable) {
        ProductFilterDto filterDto = (ProductFilterDto) dto;
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                filterDto.getSort() == null ? Sort.by("created_at").ascending() : filterDto.getSort().equalsIgnoreCase("asc") ? Sort.by("price").ascending() : Sort.by("price").descending());
        return productRepository.findAllByFilter(dto, pageRequest);
    }

    @Override
    public List<Product> findAllByFilter(HttpServletRequest request) {
        return null;
    }
}