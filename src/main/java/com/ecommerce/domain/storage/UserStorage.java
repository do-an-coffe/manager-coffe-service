package com.ecommerce.domain.storage;

import com.ecommerce.domain.entities.author.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class UserStorage extends BaseStorage{
  public User findByEmail(String email){
    return userRepository.findByEmail(email);
  }

  public User findByUserId(Long id){
    return userRepository.findByUserId(id);
  }

  public boolean existsByEmail(String email){
    return userRepository.existsByEmail(email);
  }

  public void save(User user){
    userRepository.save(user);
  }
}
