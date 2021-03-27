package com.upgrad.FoodOrderingApp.api.controller;

import com.upgrad.FoodOrderingApp.api.model.CouponDetailsResponse;
import com.upgrad.FoodOrderingApp.api.model.CustomerOrderResponse;
import com.upgrad.FoodOrderingApp.api.model.SaveOrderRequest;
import com.upgrad.FoodOrderingApp.api.model.SaveOrderResponse;
import com.upgrad.FoodOrderingApp.service.exception.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/")
public class OrderController {

  @RequestMapping(
      method = RequestMethod.GET,
      path = "/order/coupon/{coupon_name}",
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<CouponDetailsResponse> getCouponByCouponName(
      @PathVariable("coupon_name") final String couponName,
      @RequestHeader("authorization") final String authorization)
      throws AuthorizationFailedException, CouponNotFoundException {
    return null;
  }

  @RequestMapping(
      method = RequestMethod.GET,
      path = "/order",
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<CustomerOrderResponse> getPastOrdersOfUser(
      @RequestHeader("authorization") final String authorization)
      throws AuthorizationFailedException {
    return null;
  }

  @RequestMapping(method = RequestMethod.POST, path = "/order")
  public ResponseEntity<SaveOrderResponse> saveOrder(
      @RequestHeader("authorization") final String authorization,
      final SaveOrderRequest saveOrderRequest)
      throws AuthorizationFailedException, CouponNotFoundException, AddressNotFoundException,
          PaymentMethodNotFoundException, RestaurantNotFoundException, ItemNotFoundException {
    return null;
  }
}
