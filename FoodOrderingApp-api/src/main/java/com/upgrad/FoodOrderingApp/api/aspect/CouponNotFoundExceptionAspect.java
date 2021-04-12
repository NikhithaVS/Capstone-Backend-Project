package com.upgrad.FoodOrderingApp.api.aspect;

import com.upgrad.FoodOrderingApp.api.model.ErrorResponse;
import com.upgrad.FoodOrderingApp.service.exception.CouponNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CouponNotFoundExceptionAspect {
  @ExceptionHandler(CouponNotFoundException.class)
  public ResponseEntity<ErrorResponse> CouponNotFoundException(
      CouponNotFoundException exc, WebRequest webRequest) {
    final ErrorResponse errorResponse =
        new ErrorResponse().code(exc.getCode()).message(exc.getErrorMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }
}
