package com.upgrad.FoodOrderingApp.api.aspect;

import com.upgrad.FoodOrderingApp.api.model.ErrorResponse;
import com.upgrad.FoodOrderingApp.service.exception.SignUpRestrictedException;
import com.upgrad.FoodOrderingApp.service.exception.UpdateCustomerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class UpdateCustomerExceptionAspect {
    @ExceptionHandler(UpdateCustomerException .class)
    public ResponseEntity<ErrorResponse> updateCustomerExceptionHandler(UpdateCustomerException ex, WebRequest request){
        return new ResponseEntity<ErrorResponse>(new ErrorResponse().code(ex.getCode()).message(ex.getErrorMessage()), HttpStatus.FORBIDDEN);
    }
}
