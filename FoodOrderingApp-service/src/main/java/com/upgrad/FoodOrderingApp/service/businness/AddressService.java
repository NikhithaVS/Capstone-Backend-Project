package com.upgrad.FoodOrderingApp.service.businness;

import com.upgrad.FoodOrderingApp.service.dao.*;
import com.upgrad.FoodOrderingApp.service.entity.*;
import com.upgrad.FoodOrderingApp.service.exception.AddressNotFoundException;
import com.upgrad.FoodOrderingApp.service.exception.AuthorizationFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    AddressDao addressDao; //Handles all data related to the addressEntity

    @Autowired
    CustomerAuthDao customerAuthDao; //Handles all data related to the customerAuthEntity

    @Autowired
    CustomerAddressDao customerAddressDao; //Handles all Data of CustomerAddressEntity

    @Autowired
    OrderDao orderDao; //Handles all Data of Orders Entity

    /*This method is to getAddressByUUID of the customerEntity & using Address UUID.This method returns Address Entity.If error throws exception with error code and error message.
     */
    public AddressEntity getAddressByUUID(String addressUuid, CustomerEntity customerEntity)throws AuthorizationFailedException, AddressNotFoundException {
        if(addressUuid == null){//Check for Address UUID not being empty
            throw new AddressNotFoundException("ANF-005","Address id can not be empty");
        }

        //Calls getAddressByUuid method of addressDao to get addressEntity
        AddressEntity addressEntity = addressDao.getAddressByUuid(addressUuid);
        if (addressEntity == null){//Checking if null throws corresponding exception.
            throw new AddressNotFoundException("ANF-003","No address by this id");
        }

        //Getting CustomerAddressEntity by address
        CustomerAddressEntity customerAddressEntity = customerAddressDao.getCustomerAddressByAddress(addressEntity);

        //Checking if the address belong to the customer requested.If no throws corresponding exception.
        if(customerAddressEntity.getCustomer().getUuid() == customerEntity.getUuid()){
            return addressEntity;
        }else{
            throw new AuthorizationFailedException("ATHR-004","You are not authorized to view/update/delete any one else's address");
        }

    }
}