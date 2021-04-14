package com.upgrad.FoodOrderingApp.api.aspect;

import com.upgrad.FoodOrderingApp.api.model.ErrorResponse;
import com.upgrad.FoodOrderingApp.service.exception.InvalidRatingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class InvalidRatingExceptionAspect {

  @ExceptionHandler(InvalidRatingException.class)
  public ResponseEntity<ErrorResponse> InvalidRatingException(
      InvalidRatingException exc, WebRequest request) {
    return new ResponseEntity<ErrorResponse>(
        new ErrorResponse().code(exc.getCode()).message(exc.getErrorMessage()),
        HttpStatus.BAD_REQUEST);
  }
}