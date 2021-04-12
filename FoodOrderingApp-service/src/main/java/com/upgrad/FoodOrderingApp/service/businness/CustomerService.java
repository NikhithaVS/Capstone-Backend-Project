package com.upgrad.FoodOrderingApp.service.businness;

import com.upgrad.FoodOrderingApp.service.entity.CustomerAuthEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import com.upgrad.FoodOrderingApp.service.exception.AuthenticationFailedException;
import com.upgrad.FoodOrderingApp.service.exception.AuthorizationFailedException;
import com.upgrad.FoodOrderingApp.service.exception.SignUpRestrictedException;
import com.upgrad.FoodOrderingApp.service.exception.UpdateCustomerException;

public interface CustomerService {

  public CustomerEntity getCustomer(final String accessToken) throws AuthorizationFailedException;

  public CustomerEntity saveCustomer(CustomerEntity customerEntity)
      throws SignUpRestrictedException;

  public CustomerAuthEntity authenticate(final String contactNumber, final String password)
      throws AuthenticationFailedException;

  public CustomerAuthEntity logout(final String accessToken) throws AuthorizationFailedException;

  public CustomerEntity updateCustomer(CustomerEntity customerEntity);

  public CustomerEntity updateCustomerPassword(
      final String oldPassword, final String newPassword, CustomerEntity customerEntity)
      throws UpdateCustomerException;
}
