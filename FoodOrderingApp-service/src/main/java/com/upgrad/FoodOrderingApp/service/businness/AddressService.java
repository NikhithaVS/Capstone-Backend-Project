package com.upgrad.FoodOrderingApp.service.businness;

import com.upgrad.FoodOrderingApp.service.common.UnexpectedException;
import com.upgrad.FoodOrderingApp.service.dao.AddressDao;
import com.upgrad.FoodOrderingApp.service.dao.StateDao;
import com.upgrad.FoodOrderingApp.service.entity.AddressEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import com.upgrad.FoodOrderingApp.service.entity.StateEntity;
import com.upgrad.FoodOrderingApp.service.exception.AddressNotFoundException;
import com.upgrad.FoodOrderingApp.service.exception.AuthorizationFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AddressService {

    @Autowired
    AddressDao addressDao;

    @Autowired
    private StateDao stateDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public AddressEntity createAddress(
            String flatBuildingName,
            String locality,
            String city,
            String pinCode,
            StateEntity stateEntity
    ) {

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setUuid(UUID.randomUUID().toString());
        addressEntity.setActive(1);
        addressEntity.setCity(city);
        addressEntity.setFlatBuildNumber(flatBuildingName);
        addressEntity.setLocality(locality);
        addressEntity.setPincode(pinCode);
        addressEntity.setState(stateEntity);

        addressDao.createAddress(addressEntity);
        return addressEntity;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<AddressEntity> getAllAddress(CustomerEntity customerEntity) {

        // Retrieve list of customer addresses from database
        List<AddressEntity> addresses =
                customerEntity.getAddresses().stream()
                        .filter(address -> address.getActive() == 1)
                        .sorted(Comparator.comparing(AddressEntity::getId, Comparator.reverseOrder()))
                        .collect(Collectors.toList());

        return addresses;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public AddressEntity getAddressByUUID(String addressId, CustomerEntity customerEntity)
            throws AddressNotFoundException, AuthorizationFailedException {
        if (addressId == null) {
            throw new AddressNotFoundException("ANF_005", "Address id can not be empty");
        }
        AddressEntity address = addressDao.getAddressByAddressId(addressId);
        if (address == null) {
            throw new AddressNotFoundException("ANF_003", "No address by this id");
        }
        AddressEntity customerAddress =
                customerEntity.getAddresses().stream()
                        .filter(addressEntity -> addressEntity.getUuid().equals(address.getUuid()))
                        .findFirst()
                        .orElse(null);
        if (customerAddress == null) {
            throw new AuthorizationFailedException("ATHR_004", "You are not authorized to view/update/delete any one else's address ");
        }
        return address;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public AddressEntity deleteAddress(final AddressEntity address) {
        return addressDao.deleteAddress(address);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<StateEntity> getAllStates() {
        // Retrieve list of States from database
        return stateDao.getAllStates();
    }

    public StateEntity getStateByUUID(final String stateUUID) {
        return stateDao.getStateByUuid(stateUUID);
    }

}
