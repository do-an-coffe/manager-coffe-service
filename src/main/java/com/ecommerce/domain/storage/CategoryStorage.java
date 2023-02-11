package com.ecommerce.domain.storage;

import com.ecommerce.domain.entities.business.Category;
import com.ecommerce.domain.utils.cache.CacheKey;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Component
@Log4j2
public class CategoryStorage extends BaseStorage{
  private static final int CACHE_EXPIRED_TIME = 30 * 24 * 3600; //1 thang

  protected LoadingCache<Long, Category> localCache;
  @PostConstruct
  public void init() {
    localCache = Caffeine.newBuilder()
            .maximumSize(100)
            .expireAfterWrite(2, TimeUnit.HOURS)
            .refreshAfterWrite(1, TimeUnit.MINUTES)
            .build(this::findCategoryByIdRemote);
  }

  public boolean existsByName(String name){
    return categoryRepository.existsByName(name);
  }

  public Category save(Category category){
    return categoryRepository.save(category);
  }

  public Page<Category> findAll(Pageable pageable){
    return categoryRepository.findAll(pageable);
  }

  public Category findCategoryByIdRemote(Long id){
    Category category = remoteCache.get(CacheKey.genCategoryByIdKey(id), Category.class);
    if(category == null){
      category = categoryRepository.findCategoryById(id);
      if(category != null){
        remoteCache.put(CacheKey.genCategoryByIdKey(id), category);
      }
    }
    return category;
  }

  public Category findCategoryById(Long id){
    Category category = localCache.getIfPresent(id);
    if(category == null){
      category = findCategoryByIdRemote(id);
      if(category != null){
        localCache.put(id, category);
      }
    }
    return category;
  }

  public Category findCategoryByIdNotCache(Long id){
    return categoryRepository.findCategoryById(id);
  }
}
