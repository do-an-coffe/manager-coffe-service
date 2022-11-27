package com.ecommerce.domain.exceptions;

import com.ecommerce.domain.exceptions.info.BaseException;
import com.ecommerce.domain.exceptions.info.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends BaseException {
  private static final long serialVersionUID = 1L;

  public BadRequestException(String exception) {
    super(ErrorCode.BAD_REQUEST,exception);
  }
}
