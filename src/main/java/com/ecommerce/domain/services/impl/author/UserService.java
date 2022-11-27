package com.ecommerce.domain.services.impl.author;

import com.ecommerce.app.dtos.DTO;
import com.ecommerce.app.dtos.FilterDto;
import com.ecommerce.app.dtos.LoginRequest;
import com.ecommerce.app.dtos.RegisterRequest;
import com.ecommerce.app.dtos.impl.UserDto;
import com.ecommerce.app.responses.CustomPage;
import com.ecommerce.app.responses.LoginResponse;
import com.ecommerce.app.responses.UserResponse;
import com.ecommerce.domain.configs.jwt.JwtTokenProvider;
import com.ecommerce.domain.entities.CustomUserDetails;
import com.ecommerce.domain.entities.author.Role;
import com.ecommerce.domain.entities.author.User;
import com.ecommerce.domain.entities.enums.RoleType;
import com.ecommerce.domain.entities.enums.Status;
import com.ecommerce.domain.exceptions.ResourceNotFoundException;
import com.ecommerce.domain.services.base.BaseService;
import com.ecommerce.domain.services.impl.BaseAbtractService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@Log4j2
public class UserService extends BaseService {

  @Autowired
  private PasswordEncoder encoder;
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private JwtTokenProvider tokenProvider;

  //findById
  public UserDetails loadUserById(long id) {
    User user = userStorage.findByUserId(id);
    if (user == null) {
      throw new UsernameNotFoundException("not found");
    }
    return new CustomUserDetails(user);
  }

  //register
  public ResponseEntity<?> register(RegisterRequest registerRequest) {
    boolean emailExits = userStorage.existsByEmail(registerRequest.getEmail());
    if (emailExits) {
      return new ResponseEntity<>("Email have existed!", HttpStatus.BAD_REQUEST);
    }

    Role userRole =roleStorage.findByName(RoleType.USER);
    if(userRole == null){
      throw new RuntimeException("Error: Role is not found.");
    }

    User user = User.builder()
            .email(registerRequest.getEmail())
            .fullName(registerRequest.getFullname())
            .phoneNumber(registerRequest.getPhone())
            .password(encoder.encode(registerRequest.getPassword()))
            .birthday(registerRequest.getBirthday())
            .address(registerRequest.getAddress())
            .role(userRole)
            .status(Status.ACTIVE)
            .build();
    userStorage.save(user);
    return new ResponseEntity<>("Register your account success!", HttpStatus.OK);
  }

  //login
  public ResponseEntity<?> login(LoginRequest loginRequest) throws Exception {
//    User user = getUserByEmail(loginRequest.getEmail());
    User user = null;
    boolean checkAccount = encoder.matches(loginRequest.getPassword(), user.getPassword());
    if (!checkAccount) {
      return new ResponseEntity<>("Wrong password", HttpStatus.BAD_REQUEST);
    }


    if (user.getStatus().equals(Status.NON_ACTIVE)) {
      return new ResponseEntity<>("your account is not active", HttpStatus.BAD_REQUEST);
    } else {
      Authentication authentication = authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                      loginRequest.getEmail(),
                      loginRequest.getPassword()
              )
      );
      SecurityContextHolder.getContext().setAuthentication(authentication);
      String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
      return ResponseEntity.ok(new LoginResponse(jwt));
    }
  }

  public UserResponse update( DTO dto){
//    UserDto userDTO = modelMapper.map(dto, UserDto.class);
    User user = null;
//    user.setFullName(userDTO.getFullname());
//    user.setPhoneNumber(userDTO.getPhone());
//    user.setAddress(userDTO.getAddress());
//    user.setBirthday(userDTO.getBirthday());
    userStorage.save(user);
    return modelMapper.toUserResponse(user);
  }

//  @Override
//  public CustomPage<User> findAll(Pageable pageable) {
//    Page<User> userPage = userRepository.findAll(pageable);
//    return new CustomPage<>(userPage);
//  }

  public UserResponse detail(Long id){
    User user = validateUser(id);
    return modelMapper.toUserResponse(user);
  }

  public UserResponse getUserLogin() {
    User user = null;
    return modelMapper.toUserResponse(user);
  }

  public UserResponse changeStatus(Long id) {
    User user = validateUser(id);
    if (user.getStatus().equals(Status.ACTIVE)) {
      user.setStatus(Status.NON_ACTIVE);
    } else {
      user.setStatus(Status.ACTIVE);
    }
    userStorage.save(user);

    return modelMapper.toUserResponse(user);
  }

  public User validateUser(Long id){
    User user = userStorage.findByUserId(id);
    if(user == null){
      throw new ResourceNotFoundException("Not found user with id: " + id);
    }
    return user;
  }
}