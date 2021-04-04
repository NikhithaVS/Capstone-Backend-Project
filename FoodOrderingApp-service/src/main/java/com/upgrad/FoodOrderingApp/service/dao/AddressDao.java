package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.AddressEntity;

public interface AddressDao {

    public AddressEntity createAddress(AddressEntity addressEntity);
    public AddressEntity deleteAddress(AddressEntity address);
    public AddressEntity getAddressByAddressId(String addressID);
}
