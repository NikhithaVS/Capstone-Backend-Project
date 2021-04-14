package com.upgrad.FoodOrderingApp.api.utils;

import com.upgrad.FoodOrderingApp.api.model.*;
import com.upgrad.FoodOrderingApp.service.entity.*;
import org.springframework.stereotype.Component;

@Component
public class EntityDTOConverter {

  public LoginResponse convertToLoginResponseDTO(CustomerEntity customer) {
    LoginResponse loginResponse = new LoginResponse();
    loginResponse.setId(customer.getUuid());
    loginResponse.setFirstName(customer.getFirstName());
    loginResponse.setLastName(customer.getLastName());
    loginResponse.setEmailAddress(customer.getEmail());
    loginResponse.setContactNumber(customer.getContactnumber());
    loginResponse.setMessage("LOGGED IN SUCCESSFULLY");
    return loginResponse;
  }

  public UpdateCustomerResponse convertToUpdateCustomerResponseDTO(
      CustomerEntity updatedCustomerEntity) {
    UpdateCustomerResponse updateCustomerResponse = new UpdateCustomerResponse();
    updateCustomerResponse.setFirstName(updatedCustomerEntity.getFirstName());
    updateCustomerResponse.setLastName(updatedCustomerEntity.getLastName());
    updateCustomerResponse.setId(updatedCustomerEntity.getUuid());
    return updateCustomerResponse;
  }
}
