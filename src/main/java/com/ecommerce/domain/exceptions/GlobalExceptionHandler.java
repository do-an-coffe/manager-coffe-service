package com.ecommerce.domain.exceptions;

import com.ecommerce.domain.exceptions.info.BaseException;
import com.ecommerce.domain.exceptions.info.ErrorResponse;
import com.ecommerce.domain.exceptions.info.ExceptionResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<?> resourceNotFoundException(BaseException ex, WebRequest request) {
    return new ResponseEntity<>(ExceptionResponse.createFrom(ex), HttpStatus.NOT_FOUND);
  }


  @ExceptionHandler({
    ExpiredJwtException.class,
    UnsupportedJwtException.class,
    MalformedJwtException.class,
    SignatureException.class,
    UnauthorizedException.class
  })
  public ResponseEntity<?> unauthorizedException() {
    return new ResponseEntity<>(ErrorResponse.tokenNotValid(), HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request) {
//    Sentry.capture(ex);
    log.error("=======globalExceptionHandler: ", ex);
    return new ResponseEntity<>(ExceptionResponse.createFrom(ex), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @Override
  public ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    Map<String, String> invalidField = new HashMap<>();
    if (ex instanceof MethodArgumentNotValidException) {
      List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
      for (FieldError fieldError : fieldErrors) {
        invalidField.put(fieldError.getField(), fieldError.getDefaultMessage());
      }
    } else {
      invalidField.put("default", ex.getLocalizedMessage());
    }
    String message = invalidField.entrySet().iterator().next().getValue();
    return new ResponseEntity<>(
        ErrorResponse.notValid(message, invalidField), new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }
}
