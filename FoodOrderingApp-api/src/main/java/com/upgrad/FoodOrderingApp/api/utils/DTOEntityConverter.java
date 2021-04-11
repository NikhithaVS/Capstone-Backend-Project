package com.upgrad.FoodOrderingApp.api.utils;

import com.upgrad.FoodOrderingApp.api.model.SignupCustomerRequest;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DTOEntityConverter {

  public CustomerEntity convertToCustomerEntity(SignupCustomerRequest signupCustomerRequest) {
    final CustomerEntity customerEntity = new CustomerEntity();
    customerEntity.setUuid(UUID.randomUUID().toString());
    customerEntity.setFirstName(signupCustomerRequest.getFirstName());
    customerEntity.setLastName(signupCustomerRequest.getLastName());
    customerEntity.setEmail(signupCustomerRequest.getEmailAddress());
    customerEntity.setPassword(signupCustomerRequest.getPassword());
    customerEntity.setContactNumber(signupCustomerRequest.getContactNumber());
    return customerEntity;
  }
}
