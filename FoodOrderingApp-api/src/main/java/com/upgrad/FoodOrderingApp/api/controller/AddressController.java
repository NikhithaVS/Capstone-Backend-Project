package com.upgrad.FoodOrderingApp.api.controller;

import com.upgrad.FoodOrderingApp.api.model.*;
import com.upgrad.FoodOrderingApp.service.businness.AddressService;
import com.upgrad.FoodOrderingApp.service.common.Util;
import com.upgrad.FoodOrderingApp.service.entity.AddressEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import com.upgrad.FoodOrderingApp.service.entity.StateEntity;
import com.upgrad.FoodOrderingApp.service.exception.AddressNotFoundException;
import com.upgrad.FoodOrderingApp.service.exception.AuthorizationFailedException;
import com.upgrad.FoodOrderingApp.service.exception.SaveAddressException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;


@CrossOrigin
@RestController
@RequestMapping("/")
public class AddressController {

    @Autowired
    AddressService addressService;

    @CrossOrigin
    @RequestMapping(
            method = RequestMethod.POST,
            path = "/address",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SaveAddressResponse> saveAddress(
            @RequestHeader("authorization") final String authorization,
            @RequestBody(required = false) final SaveAddressRequest saveAddressRequest)
            throws AuthorizationFailedException, AddressNotFoundException, SaveAddressException {


        final String accessToken = Util.getBearerAuthToken(authorization);

        // ToDo: check if the access token exists in the database

        StateEntity stateEntity = addressService.getStateByUUID(saveAddressRequest.getStateUuid());

        AddressEntity savedAddress = addressService.createAddress(
                saveAddressRequest.getFlatBuildingName(),
                saveAddressRequest.getLocality(),
                saveAddressRequest.getCity(),
                saveAddressRequest.getPincode(), stateEntity);

        SaveAddressResponse addressResponse =
                new SaveAddressResponse()
                        .id(savedAddress.getUuid())
                        .status("Success");

        return new ResponseEntity<SaveAddressResponse>(addressResponse, HttpStatus.CREATED);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/address/customer",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<AddressListResponse> getAllSavedAddresses(
            @RequestHeader("authorization") final String authorization)
            throws AuthorizationFailedException {

        final String accessToken = Util.getBearerAuthToken(authorization);

        // ToDo: check if the access token exists in the database
        CustomerEntity customerEntity = new CustomerEntity();

        List<AddressEntity> addressesInSortedOrder = addressService.getAllAddress(customerEntity);

        List<AddressList> allAddresses = new ArrayList<>();

        addressesInSortedOrder.forEach(
                address -> {
                    AddressListState addressListState = new AddressListState();
                    addressListState.setId(UUID.fromString(address.getState().getUuid()));
                    addressListState.setStateName(address.getState().getStateName());

                    AddressList addressList =
                            new AddressList()
                                    .id(UUID.fromString(address.getUuid()))
                                    .flatBuildingName(address.getFlatBuildNumber())
                                    .city(address.getCity())
                                    .locality(address.getLocality())
                                    .pincode(address.getPincode())
                                    .state(addressListState);
                    allAddresses.add(addressList);
                });

        AddressListResponse addressListResponse = new AddressListResponse().addresses(allAddresses);
        return new ResponseEntity<AddressListResponse>(addressListResponse, HttpStatus.OK);

    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "/address/{address_id}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<DeleteAddressResponse> deleteSavedAddress(
            @PathVariable("address_id") final String addressID,
            @RequestHeader("authorization") final String authorization)
            throws AuthorizationFailedException, AddressNotFoundException {

        final String accessToken = Util.getBearerAuthToken(authorization);

        // ToDo: Verify access token exists in the database
        final CustomerEntity customerEntity = new CustomerEntity();

        // Get address entity of address to be deleted
        AddressEntity address = addressService.getAddressByUUID(addressID, customerEntity);

        AddressEntity deletedAddress;

        DeleteAddressResponse deleteAddressResponse = new DeleteAddressResponse();

        deletedAddress = addressService.deleteAddress(address);

        deleteAddressResponse.status("deleted");
        deleteAddressResponse.id(UUID.fromString(deletedAddress.getUuid()));
        return new ResponseEntity<DeleteAddressResponse>(deleteAddressResponse, HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/states",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<StatesListResponse> getAllStates() {

        List<StateEntity> states = addressService.getAllStates();

        if (!states.isEmpty()) {
            List<StatesList> allStates = new LinkedList<>();
            states.forEach(
                    state -> {
                        StatesList stateList = new StatesList();
                        stateList.setId(UUID.fromString(state.getUuid()));
                        stateList.setStateName(state.getStateName());

                        allStates.add(stateList);
                    });
            StatesListResponse statesListResponse = new StatesListResponse().states(allStates);
            return new ResponseEntity<StatesListResponse>(statesListResponse, HttpStatus.OK);
        } else
            return new ResponseEntity<StatesListResponse>(new StatesListResponse(), HttpStatus.OK);
    }
}
