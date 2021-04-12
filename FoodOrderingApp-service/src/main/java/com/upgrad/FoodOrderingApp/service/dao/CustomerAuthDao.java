package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.CustomerAuthEntity;

public interface CustomerAuthDao {

  public CustomerAuthEntity getCustomerAuthTokenByAccessToken(final String authorization);

  public CustomerAuthEntity createCustomerAuth(CustomerAuthEntity customerAuthEntity);

  public CustomerAuthEntity customerLogout(CustomerAuthEntity customerAuthEntity);
}
