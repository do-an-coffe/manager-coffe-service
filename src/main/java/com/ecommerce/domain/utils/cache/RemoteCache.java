package com.ecommerce.domain.utils.cache;

import com.ecommerce.domain.utils.JsonParser;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@Component
@Log4j2
public class RemoteCache {
  public static final int CACHE_DURATION_DEFAULT = 3600; // 1 tieng
  @Autowired
  private RedisTemplate<String, String> redisTemplate;
  public void put(String key, String value, int expireTime) {
    redisTemplate.opsForValue().set(key, value, (long) expireTime, TimeUnit.SECONDS);
  }

  public void put(String key, Object object, int expireTime) {
    //log.debug("=======save cache " + key + ": ", JsonParser.toJson(object));
    try {
      put(key, JsonParser.toJson(object), expireTime);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void put(String key, Object object) {
    //log.debug("=======save cache " + key + ": ", object);
    try {
      put(key, JsonParser.toJson(object), CACHE_DURATION_DEFAULT);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void put(String key, String value) {
    redisTemplate.opsForValue().set(key, value);
  }

  public String get(String key) {
    return redisTemplate.opsForValue().get(key);
  }

  public <T> ArrayList<T> getList(String key, Class<T> tClass) {
    try {
      String value = redisTemplate.opsForValue().get(key);
      return JsonParser.arrayList(value, tClass);
    } catch (Exception e) {
      return null;
    }
  }

  public <T> T get(String key, Class<T> tClass) {
    try {
      String value = redisTemplate.opsForValue().get(key);
      return JsonParser.entity(value, tClass);
    } catch (Exception e) {
      return null;
    }
  }
}
