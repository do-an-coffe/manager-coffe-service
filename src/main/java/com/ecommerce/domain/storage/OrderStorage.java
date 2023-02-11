package com.ecommerce.domain.storage;

import com.ecommerce.domain.entities.business.Category;
import com.ecommerce.domain.entities.business.Discount;
import com.ecommerce.domain.entities.business.Order;
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
public class OrderStorage extends BaseStorage{

  protected LoadingCache<Long, Order> localCache;
  @PostConstruct
  public void init() {
    localCache = Caffeine.newBuilder()
            .maximumSize(100)
            .expireAfterWrite(2, TimeUnit.HOURS)
            .refreshAfterWrite(1, TimeUnit.MINUTES)
            .build(this::findOrderByIdRemote);
  }
  public Page<Order> findAll(Pageable pageable){
    return orderRepository.findAll(pageable);
  }


  // k dung
  public Order findOrdersById(Long id){
    return orderRepository.findOrdersById(id);
  }

  public Order findOrderByIdRemote(Long id){
    Order order = remoteCache.get(CacheKey.genOrderByIdKey(id), Order.class);
    if(order == null){
      order = orderRepository.findOrdersById(id);
      if(order != null){
        remoteCache.put(CacheKey.genOrderByIdKey(id), order);
      }
    }
    return order;
  }

  public Order findOrderById(Long id){
    Order order = localCache.getIfPresent(id);
    if(order == null){
      order = findOrderByIdRemote(id);
      if(order != null){
        localCache.put(id, order);
      }
    }
    return order;
  }


  public Order save(Order order){
    return orderRepository.save(order);
  }

  public void delete(Order order){
    orderRepository.delete(order);
  }
}
