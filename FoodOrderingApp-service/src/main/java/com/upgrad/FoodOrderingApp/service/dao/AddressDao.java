package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.AddressEntity;

import java.util.List;

public interface AddressDao {

  public AddressEntity saveAddress(final AddressEntity address);
  public List<AddressEntity> getAddressesByCustomerUuid(final String customerUuid);
  public AddressEntity deleteAddress(final AddressEntity addressEntity);
  public AddressEntity getAddressByUuid(final String addressId);
}
