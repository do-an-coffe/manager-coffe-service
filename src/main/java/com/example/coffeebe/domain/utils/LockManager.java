package com.example.coffeebe.domain.utils;

import lombok.extern.log4j.Log4j2;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Log4j2
public class LockManager {

  public static final int WAIT_TIME = 5; // the maximum time to aquire the lock

  public static final int TIME_LOCK_IN_SECOND = 10; // maximum time to lock

  public String redisPrefixKey = "ecommerce:";

  @Autowired(required = false)
  private RedissonClient client;

  public RLock startLockProduct(Long id) {
    RLock lock = client.getLock(redisPrefixKey + ":lock:product:" + id);
    lock.lock(TIME_LOCK_IN_SECOND, TimeUnit.SECONDS);
    return lock;
  }

  public void unLock(RLock lock) {
    if (lock != null) {
      lock.unlockAsync();
    }
  }

}
