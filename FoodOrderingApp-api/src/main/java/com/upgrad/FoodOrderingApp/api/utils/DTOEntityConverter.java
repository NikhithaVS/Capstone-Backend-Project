package com.upgrad.FoodOrderingApp.api.utils;

import com.upgrad.FoodOrderingApp.api.model.SaveAddressRequest;
import com.upgrad.FoodOrderingApp.api.model.SignupCustomerRequest;
import com.upgrad.FoodOrderingApp.api.model.SignupCustomerResponse;
import com.upgrad.FoodOrderingApp.service.entity.AddressEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import com.upgrad.FoodOrderingApp.service.entity.StateEntity;
import org.springframework.context.support.AbstractRefreshableConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class DTOEntityConverter {

  public CustomerEntity convertToCustomerEntity(SignupCustomerRequest signupCustomerRequest) {
    CustomerEntity customer = new CustomerEntity();
    customer.setUuid(UUID.randomUUID().toString());
    customer.setFirstName(signupCustomerRequest.getFirstName());
    customer.setLastName(signupCustomerRequest.getLastName());
    customer.setContactNumber(signupCustomerRequest.getContactNumber());
    customer.setEmail(signupCustomerRequest.getEmailAddress());
    customer.setPassword(signupCustomerRequest.getPassword());
    //doubt
    List<AddressEntity> addressList=new ArrayList<>();
    customer.setAddresses(addressList);
    return customer;
  }

  public AddressEntity convertToAddressEntity(
      SaveAddressRequest saveAddressRequest, StateEntity stateEntity,CustomerEntity customerEntity) {
    AddressEntity newAddressEntity = new AddressEntity();
    newAddressEntity.setUuid(UUID.randomUUID().toString());
    newAddressEntity.setFlatBuildNumber(saveAddressRequest.getFlatBuildingName());
    newAddressEntity.setLocality(saveAddressRequest.getLocality());
    newAddressEntity.setPincode(saveAddressRequest.getPincode());
    newAddressEntity.setCity(saveAddressRequest.getCity());
    newAddressEntity.setState(stateEntity);
    //Doubt
    newAddressEntity.setActive(1);
    //Doubt
    List<CustomerEntity> customersList=new ArrayList<>();
    customersList.add(customerEntity);
    newAddressEntity.setCustomers(customersList);
    return newAddressEntity;
  }
}
