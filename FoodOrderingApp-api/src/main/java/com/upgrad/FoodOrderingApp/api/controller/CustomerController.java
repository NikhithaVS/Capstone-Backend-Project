package com.upgrad.FoodOrderingApp.api.controller;

import com.upgrad.FoodOrderingApp.api.model.*;
import com.upgrad.FoodOrderingApp.api.utils.DTOEntityConverter;
import com.upgrad.FoodOrderingApp.service.businness.AuthenticationService;
import com.upgrad.FoodOrderingApp.service.businness.CustomerService;
import com.upgrad.FoodOrderingApp.service.businness.PasswordCryptographyProvider;
import com.upgrad.FoodOrderingApp.service.businness.SignUpBusinessService;
import com.upgrad.FoodOrderingApp.service.entity.CustomerAuthEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import com.upgrad.FoodOrderingApp.service.exception.AuthenticationFailedException;
import com.upgrad.FoodOrderingApp.service.exception.AuthorizationFailedException;
import com.upgrad.FoodOrderingApp.service.exception.SignUpRestrictedException;
import com.upgrad.FoodOrderingApp.service.exception.UpdateCustomerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.Base64;

@CrossOrigin
@RestController
@RequestMapping("/")
public class CustomerController {

    @Autowired CustomerService customerService;
    @Autowired DTOEntityConverter dtoEntityConverter;
    @Autowired AuthenticationService authenticationService;
    @Autowired
    SignUpBusinessService signUpBusinessService;
    @Autowired private PasswordCryptographyProvider cryptographyProvider;
    @RequestMapping(
            method = RequestMethod.POST,
            path = "/customer/signup",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<SignupCustomerResponse> signup(
            final SignupCustomerRequest signupCustomerRequest) throws SignUpRestrictedException {

        if (customerService.getCustomerByContactNumber(signupCustomerRequest.getContactNumber())
                != null)
            throw new SignUpRestrictedException(
                    "SGR-001", "This contact number is already registered! Try other contact number.");

        if (signupCustomerRequest.getFirstName().isEmpty()
                || signupCustomerRequest.getContactNumber().isEmpty()
                || signupCustomerRequest.getEmailAddress().isEmpty()
                || signupCustomerRequest.getPassword().isEmpty())
            throw new SignUpRestrictedException(
                    "SGR-005", "Except last name all fields should be filled");


        CustomerEntity customerEntity = dtoEntityConverter.convertToCustomerEntity(signupCustomerRequest);
        final CustomerEntity savedCustomer=signUpBusinessService.signup(customerEntity);
        SignupCustomerResponse signupCustomerResponse =
                new SignupCustomerResponse()
                        .id(savedCustomer.getUuid())
                        .status("CUSTOMER SUCCESSFULLY REGISTERED");
        return new ResponseEntity<>(signupCustomerResponse, HttpStatus.CREATED);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/customer/login",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<LoginResponse> login(
            @RequestHeader("authorization") final String authorization)
            throws AuthenticationFailedException {
        if (!authorization.contains("Basic"))
            throw new AuthenticationFailedException(
                    "ATH-003", "Incorrect format of decoded customer name and password");
        byte[] decode = Base64.getDecoder().decode(authorization.split("Basic ")[1]);

        String decodedText = new String(decode);
        String[] decodedArray = decodedText.split(":");

        CustomerAuthEntity customerAuthEntity =
                authenticationService.authenticate(decodedArray[0], decodedArray[1]);
        CustomerEntity customerEntity = customerAuthEntity.getCustomer();

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setId(customerEntity.getUuid());
        loginResponse.setMessage("LOGGED IN SUCCESSFULLY");
        loginResponse.setContactNumber(customerEntity.getContactNumber());
        loginResponse.setFirstName(customerEntity.getFirstName());
        loginResponse.setLastName(customerEntity.getLastName());
        loginResponse.setEmailAddress(customerEntity.getEmail());

        HttpHeaders headers = new HttpHeaders();

        headers.add("JwtAccessToken", customerAuthEntity.getAccessToken());
        return new ResponseEntity<LoginResponse>(loginResponse, headers, HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/customer/logout",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<LogoutResponse> logout(
            @RequestHeader("authorization") final String authorization)
            throws AuthorizationFailedException {

        CustomerAuthEntity customerAuthEntity = authenticationService.validateToken(authorization);
        customerAuthEntity.setLogoutAt(ZonedDateTime.now());
        authenticationService.updateCustomerToken(customerAuthEntity);
        LogoutResponse logoutResponse = new LogoutResponse();
        logoutResponse.setId(customerAuthEntity.getCustomer().getUuid());
        logoutResponse.setMessage("LOGGED OUT SUCCESSFULLY");

        return new ResponseEntity<>(logoutResponse, HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            path = "/customer",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UpdateCustomerResponse> update(
            @RequestHeader("authorization") final String authorization,
            final UpdateCustomerRequest updateCustomerRequest)
            throws UpdateCustomerException, AuthorizationFailedException {

        String accessToken = authorization.split("Bearer ")[1];
        CustomerAuthEntity customerAuthEntity=authenticationService.validateToken(accessToken);
        if(updateCustomerRequest.getFirstName().isEmpty())
            throw new UpdateCustomerException("UCR-002","First name field should not be empty");

        CustomerEntity
                savedCustomer=customerService.getCustomerByContactNumber(customerAuthEntity.getCustomer().getContactNumber());
        savedCustomer.setFirstName(updateCustomerRequest.getFirstName());
        savedCustomer.setLastName(updateCustomerRequest.getLastName());
        customerService.updateCustomer(savedCustomer);
        UpdateCustomerResponse updateCustomerResponse=new UpdateCustomerResponse();
        updateCustomerResponse.setId(savedCustomer.getUuid());
        updateCustomerResponse.setFirstName(updateCustomerRequest.getFirstName());
        updateCustomerResponse.setLastName(updateCustomerRequest.getLastName());
        updateCustomerResponse.setStatus("CUSTOMER DETAILS UPDATED SUCCESSFULLY");
        return new ResponseEntity<UpdateCustomerResponse>(updateCustomerResponse,HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            path = "/customer/password",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UpdatePasswordResponse> changePassword(
            @RequestHeader("authorization") final String authorization,
            final UpdatePasswordRequest updatePasswordRequest)
            throws UpdateCustomerException, AuthorizationFailedException, SignUpRestrictedException {

        CustomerAuthEntity customerAuthEntity=authenticationService.validateToken(authorization);
        if(updatePasswordRequest.getNewPassword().isEmpty()||updatePasswordRequest.getOldPassword().isEmpty())
            throw new UpdateCustomerException("UCR-003","No field should be empty");

        CustomerEntity customerEntity =customerAuthEntity.getCustomer();
        final String encryptedPassword =
                cryptographyProvider.encrypt(updatePasswordRequest.getOldPassword(), customerEntity.getSalt());

        if(!customerEntity.getPassword().equals(encryptedPassword))
            throw new UpdateCustomerException("UCR-004","Incorrect old password!");

        if(signUpBusinessService.passwordStrength(updatePasswordRequest.getNewPassword())){
            customerEntity.setPassword(encryptedPassword);
            customerService.updateCustomer(customerEntity);
        }
        UpdatePasswordResponse updatePasswordResponse=new UpdatePasswordResponse();
        updatePasswordResponse.setId(customerEntity.getUuid());
        updatePasswordResponse.setStatus("CUSTOMER PASSWORD UPDATED SUCCESSFULLY");
        return new ResponseEntity<UpdatePasswordResponse>(updatePasswordResponse,HttpStatus.OK);
    }
}
