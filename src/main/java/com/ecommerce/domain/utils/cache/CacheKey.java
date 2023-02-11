package com.ecommerce.domain.utils.cache;
public class CacheKey{
  public static String PREFIX = "ecommerce";
  public static String genCategoryByIdKey(Long id){
    return PREFIX + ":category:id:" + id;
  }

  public static String genOrderByIdKey(Long id){
    return PREFIX + ":order:id:" + id;
  }
}
