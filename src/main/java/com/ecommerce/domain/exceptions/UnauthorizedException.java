package com.ecommerce.domain.exceptions;

import com.ecommerce.domain.exceptions.info.BaseException;
import com.ecommerce.domain.exceptions.info.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends BaseException {
  public UnauthorizedException() {
    super(ErrorCode.UNAUTHORIZED, "Unauthorized");
  }
}
