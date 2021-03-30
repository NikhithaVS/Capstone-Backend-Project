package com.upgrad.FoodOrderingApp.api.utils;

import com.upgrad.FoodOrderingApp.api.model.SignupCustomerRequest;
import com.upgrad.FoodOrderingApp.api.model.SignupCustomerResponse;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DTOEntityConverter {

    public CustomerEntity convertToCustomerEntity(SignupCustomerRequest signupCustomerRequest) {
        CustomerEntity customer = new CustomerEntity();
        customer.setUuid(UUID.randomUUID().toString());
        customer.setFirstName(signupCustomerRequest.getFirstName());
        customer.setLastName(signupCustomerRequest.getLastName());
        customer.setContactNumber(signupCustomerRequest.getContactNumber());
        customer.setEmail(signupCustomerRequest.getEmailAddress());
        customer.setPassword(signupCustomerRequest.getPassword());
        return customer;
    }
}
