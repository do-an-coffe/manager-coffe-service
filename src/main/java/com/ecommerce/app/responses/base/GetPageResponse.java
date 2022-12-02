package com.ecommerce.app.responses.base;

import lombok.Data;

import java.util.List;

@Data
public class GetPageResponse<T> {
  public final List<T> data;
  public final Metadata metadata;
}
