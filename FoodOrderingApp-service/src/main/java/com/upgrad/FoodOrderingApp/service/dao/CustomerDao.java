package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.CustomerAuthEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;

public interface CustomerDao {
  public CustomerEntity createCustomer(CustomerEntity customerEntity);

  public CustomerEntity getCustomerByContactNumber(final String contactNumber);

  public CustomerAuthEntity createAuthToken(final CustomerAuthEntity userAuthTokenEntity);

  public CustomerEntity updateCustomer(CustomerEntity customerToBeUpdated);

  public CustomerEntity getCustomerByUuid(final String uuid);
}
