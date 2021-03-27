package com.upgrad.FoodOrderingApp.api.controller;

import com.upgrad.FoodOrderingApp.api.model.*;
import com.upgrad.FoodOrderingApp.service.exception.AuthenticationFailedException;
import com.upgrad.FoodOrderingApp.service.exception.AuthorizationFailedException;
import com.upgrad.FoodOrderingApp.service.exception.SignUpRestrictedException;
import com.upgrad.FoodOrderingApp.service.exception.UpdateCustomerException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/")
public class CustomerController {

  @RequestMapping(
      method = RequestMethod.POST,
      path = "/customer/signup",
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<SignupCustomerResponse> signup(
      final SignupCustomerRequest signupCustomerRequest) throws SignUpRestrictedException {
    return null;
  }

  @RequestMapping(
      method = RequestMethod.POST,
      path = "/customer/login",
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<LoginResponse> login(
      @RequestHeader("authorization") final String authorization)
      throws AuthenticationFailedException {
    return null;
  }

  @RequestMapping(
      method = RequestMethod.POST,
      path = "/customer/logout",
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<LogoutResponse> logout(
      @RequestHeader("authorization") final String authorization)
      throws AuthorizationFailedException {
    return null;
  }

  @RequestMapping(
      method = RequestMethod.PUT,
      path = "/customer",
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<UpdateCustomerResponse> update(
      @RequestHeader("authorization") final String authorization,
      final UpdateCustomerRequest updateCustomerRequest)
      throws UpdateCustomerException, AuthorizationFailedException {
    return null;
  }

  @RequestMapping(
      method = RequestMethod.PUT,
      path="/customer/password",
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<UpdatePasswordResponse> changePassword(
      @RequestHeader("authorization") final String authorization,
      final UpdatePasswordRequest updatePasswordRequest)
      throws UpdateCustomerException, AuthorizationFailedException {
    return null;
  }
}
