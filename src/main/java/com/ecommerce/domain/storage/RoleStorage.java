package com.ecommerce.domain.storage;

import com.ecommerce.domain.entities.author.Role;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class RoleStorage extends BaseStorage{

  public Role findByName(String name){
    return roleRepository.findByName(name);
  }
}
