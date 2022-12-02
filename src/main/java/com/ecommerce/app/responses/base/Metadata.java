package com.ecommerce.app.responses.base;

import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class Metadata {

  private final int page;
  private final long size;
  private final long total;
  private final long totalPage;

  public static Metadata createFrom(Page page) {
    return new Metadata(
        page.getNumber(),
        page.getSize(),
        page.getTotalElements(),
        page.getTotalPages());
  }
}
