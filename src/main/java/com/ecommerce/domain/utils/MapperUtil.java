package com.ecommerce.domain.utils;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class MapperUtil {

  private ModelMapper modelMapper;

  @PostConstruct
  public void init() {
    modelMapper = new ModelMapper();
  }

  public <D, T> Page<D> mapEntityPageIntoDtoPage(Page<T> entities, Class<D> dtoClass) {
    return entities.map(objectEntity -> modelMapper.map(objectEntity, dtoClass));
  }

  public <T> Page<T> listToPage(List<T> tList, Pageable pageable) {

    int start = (int) pageable.getOffset();
    int end = (int) (Math.min((start + pageable.getPageSize()), tList.size()));

    return new PageImpl<>(tList.subList(start, end), pageable, tList.size());
  }

  public <T> Page<T> listToPage1(List<T> tList, Pageable pageable) {
    int start = (Math.min(Math.max(pageable.getPageSize(), 0), tList.size()));
    int end = (Math.min(Math.max(pageable.getPageSize(), start), tList.size()));

    return new PageImpl<T>(tList.subList(start, end), pageable, tList.size());
  }
}
