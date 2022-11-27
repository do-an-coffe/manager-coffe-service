package com.ecommerce.domain.exceptions;

import com.ecommerce.domain.exceptions.info.BaseException;
import com.ecommerce.domain.exceptions.info.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends BaseException {
  private static final long serialVersionUID = 1L;

  public ResourceNotFoundException(String exception) {
    super(ErrorCode.RESOURCE_NOT_FOUND, exception);
  }
}
