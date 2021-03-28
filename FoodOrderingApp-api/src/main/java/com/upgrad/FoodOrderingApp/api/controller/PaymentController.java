package com.upgrad.FoodOrderingApp.api.controller;

import com.upgrad.FoodOrderingApp.api.model.PaymentListResponse;
import com.upgrad.FoodOrderingApp.api.model.PaymentResponse;
import com.upgrad.FoodOrderingApp.service.businness.PaymentService;
import com.upgrad.FoodOrderingApp.service.entity.PaymentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/")
public class PaymentController {

  @Autowired
  PaymentService paymentService;

  @RequestMapping(
      method = RequestMethod.GET,
      path = "/payment",
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<PaymentListResponse> getPaymentMethods() {

    List<PaymentEntity> allPaymentMethods = paymentService.getAllPaymentMethods();

    PaymentListResponse resp = new PaymentListResponse();
    allPaymentMethods.forEach(
            paymentEntity ->
                    resp.addPaymentMethodsItem(
                            new PaymentResponse()
                                    .id(UUID.fromString(paymentEntity.getUuid()))
                                    .paymentName(paymentEntity.getPaymentName())));

    if (resp.getPaymentMethods().isEmpty()) {
      return new ResponseEntity<PaymentListResponse>(resp, HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<PaymentListResponse>(resp, HttpStatus.OK);
    }

  }
}
