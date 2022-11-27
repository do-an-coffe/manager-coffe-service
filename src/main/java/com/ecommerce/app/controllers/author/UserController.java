package com.ecommerce.app.controllers.author;

import com.ecommerce.app.responses.UserResponse;
import com.ecommerce.domain.services.impl.author.RoleService;
import com.ecommerce.domain.services.impl.author.UserService;
import com.ecommerce.app.dtos.DTO;
import com.ecommerce.app.dtos.LoginRequest;
import com.ecommerce.app.dtos.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
  private RoleService roleService;
	
	@Autowired
  private UserService userService;
  @GetMapping("/init-role")
  public void initRole(){
    roleService.initRole();
  }


  @PostMapping("/register")
  public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest){
    return userService.register(registerRequest);
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception {
    return userService.login(loginRequest);
  }

  @GetMapping("/info")
  public UserResponse getUser(){
    return userService.getUserLogin();
  }

  @GetMapping("/{id}")
  public UserResponse detail(@PathVariable Long id){

    return userService.detail(id);
  }

  @PatchMapping
  public UserResponse update(@Valid @RequestBody DTO userDto){
    return userService.update(userDto);
  }

  @PostMapping("{id}/status")
  public UserResponse changeStatus(@PathVariable Long id){
    return userService.changeStatus(id);
  }
}
