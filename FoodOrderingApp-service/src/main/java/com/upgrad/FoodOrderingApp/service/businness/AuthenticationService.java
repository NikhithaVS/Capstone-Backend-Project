package com.upgrad.FoodOrderingApp.service.businness;

import com.upgrad.FoodOrderingApp.service.entity.CustomerAuthEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import com.upgrad.FoodOrderingApp.service.exception.AuthenticationFailedException;
import com.upgrad.FoodOrderingApp.service.exception.AuthorizationFailedException;

public interface AuthenticationService {

    public CustomerAuthEntity authenticate(final String contactNumber, final String password)
            throws AuthenticationFailedException;

    public CustomerAuthEntity validateToken(String authorisation) throws AuthorizationFailedException;

    public CustomerAuthEntity updateCustomerToken(CustomerAuthEntity customerAuthEntity);

}