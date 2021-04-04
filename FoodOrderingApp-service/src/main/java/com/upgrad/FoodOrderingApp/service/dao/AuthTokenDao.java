package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.CustomerAuthEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;

public interface AuthTokenDao {

    public CustomerAuthEntity updateToken(CustomerAuthEntity customerAuthEntity);
    public CustomerAuthEntity createAuthToken(final CustomerAuthEntity customerAuthEntity);
    public CustomerAuthEntity getCustomerAuthToken(final String accessToken);
}
