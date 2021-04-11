package com.upgrad.FoodOrderingApp.service.businness;

import com.upgrad.FoodOrderingApp.service.entity.AddressEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import com.upgrad.FoodOrderingApp.service.entity.StateEntity;
import com.upgrad.FoodOrderingApp.service.exception.AddressNotFoundException;
import com.upgrad.FoodOrderingApp.service.exception.AuthorizationFailedException;
import com.upgrad.FoodOrderingApp.service.exception.SaveAddressException;

import java.util.List;

public interface AddressService {
  public StateEntity getStateByUUID(final String stateUuid)
      throws AddressNotFoundException, SaveAddressException;

  public AddressEntity saveAddress(
          final CustomerEntity customerEntity, final AddressEntity addressEntity)
      throws SaveAddressException;

  public AddressEntity deleteAddress(final AddressEntity addressEntity);

  public AddressEntity getAddressByUUID(final String addressId, final CustomerEntity customer)
      throws AuthorizationFailedException, AddressNotFoundException;

  public List<AddressEntity> getAllAddress(final CustomerEntity customerEntity);

  public List<StateEntity> getAllStates();
}
