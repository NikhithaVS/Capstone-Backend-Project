package com.upgrad.FoodOrderingApp.service.businness;

import com.upgrad.FoodOrderingApp.service.entity.AddressEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import com.upgrad.FoodOrderingApp.service.entity.StateEntity;
import com.upgrad.FoodOrderingApp.service.exception.AddressNotFoundException;
import com.upgrad.FoodOrderingApp.service.exception.AuthorizationFailedException;

import java.util.List;

public interface AddressService {
    public AddressEntity saveAddress(AddressEntity newAddressEntity);
    public List<AddressEntity> getAllAddress(CustomerEntity customerEntity);
    public AddressEntity getAddressByUUID(String addressId, CustomerEntity customerEntity)
            throws AddressNotFoundException, AuthorizationFailedException;

    public AddressEntity deleteAddress(final AddressEntity address);
    public List<StateEntity> getAllStates();
    public StateEntity getStateByUUID(final String stateUUID) throws AddressNotFoundException;
}
