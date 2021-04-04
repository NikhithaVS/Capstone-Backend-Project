package com.upgrad.FoodOrderingApp.api.controller;

import com.upgrad.FoodOrderingApp.api.model.*;
import com.upgrad.FoodOrderingApp.api.utils.DTOEntityConverter;
import com.upgrad.FoodOrderingApp.service.businness.AddressService;
import com.upgrad.FoodOrderingApp.service.businness.AuthenticationService;
import com.upgrad.FoodOrderingApp.service.entity.AddressEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerAuthEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import com.upgrad.FoodOrderingApp.service.entity.StateEntity;
import com.upgrad.FoodOrderingApp.service.exception.AddressNotFoundException;
import com.upgrad.FoodOrderingApp.service.exception.AuthorizationFailedException;
import com.upgrad.FoodOrderingApp.service.exception.SaveAddressException;
import org.apache.commons.lang3.math.NumberUtils;
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

  @Autowired AddressService addressService;

  @Autowired AuthenticationService authenticationService;

  @Autowired DTOEntityConverter dtoEntityConverter;

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

    if (saveAddressRequest.getCity().isEmpty()
        || saveAddressRequest.getFlatBuildingName().isEmpty()
        || saveAddressRequest.getLocality().isEmpty()
        || saveAddressRequest.getPincode().isEmpty())
      throw new SaveAddressException("SAR-001", "No field can be empty");

    // ToDo: check if the access token exists in the database
    final CustomerAuthEntity customerAuthEntity =
        authenticationService.validateToken(authorization);

    final CustomerEntity customerEntity=customerAuthEntity.getCustomer();

    final String pincode = saveAddressRequest.getPincode();
    if (pincode.length() != 6 && !NumberUtils.isParsable(pincode))
      throw new SaveAddressException("SAR-002", "Invalid pincode");

    StateEntity stateEntity = addressService.getStateByUUID(saveAddressRequest.getStateUuid());
    if (stateEntity == null) throw new AddressNotFoundException("ANF-002", "No state by this id");

    AddressEntity newAddress =
        dtoEntityConverter.convertToAddressEntity(saveAddressRequest, stateEntity,customerEntity);

    addressService.saveAddress(newAddress);

    SaveAddressResponse addressResponse =
        new SaveAddressResponse().id(newAddress.getUuid()).status("Success");
    return new ResponseEntity<SaveAddressResponse>(addressResponse, HttpStatus.CREATED);
  }

  @RequestMapping(
      method = RequestMethod.GET,
      path = "/address/customer",
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<AddressListResponse> getAllSavedAddresses(
      @RequestHeader("authorization") final String authorization)
      throws AuthorizationFailedException {

    final CustomerAuthEntity customerAuthEntity =
        authenticationService.validateToken(authorization);

    // ToDo: check if the access token exists in the database
    CustomerEntity customerEntity = customerAuthEntity.getCustomer();

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

    final CustomerAuthEntity customerAuthEntity =
            authenticationService.validateToken(authorization);

    // ToDo: Verify access token exists in the database
    final CustomerEntity customerEntity = customerAuthEntity.getCustomer();

    // Get address entity of address to be deleted
    AddressEntity address = addressService.getAddressByUUID(addressID, customerEntity);

    AddressEntity deletedAddress=addressService.deleteAddress(address);

    DeleteAddressResponse deleteAddressResponse = new DeleteAddressResponse();

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