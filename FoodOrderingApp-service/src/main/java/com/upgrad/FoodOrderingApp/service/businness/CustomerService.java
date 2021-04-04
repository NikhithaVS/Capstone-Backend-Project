package com.upgrad.FoodOrderingApp.service.businness;

import com.upgrad.FoodOrderingApp.service.dao.*;
import com.upgrad.FoodOrderingApp.service.entity.*;
import com.upgrad.FoodOrderingApp.service.exception.AuthorizationFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.ZonedDateTime;

@Service
public class CustomerService {

    @Autowired
    CustomerDao customerDao; //Handles all data related to the CustomerEntity

    @Autowired
    PasswordCryptographyProvider passwordCryptographyProvider; //Provides coding and decoding for the password

    @Autowired
    CustomerAuthDao customerAuthDao; //Handles all data related to the customerAuthEntity

    public CustomerEntity getCustomer(String accessToken) throws AuthorizationFailedException {
        CustomerAuthEntity customerAuthEntity = customerAuthDao.getCustomerAuthByAccessToken(accessToken);

        if (customerAuthEntity == null) {//Checking if Customer not logged In
            throw new AuthorizationFailedException("ATHR-001", "Customer is not Logged in.");
        }

        if (customerAuthEntity.getLogoutAt() != null) {//Checking if cutomer is logged Out
            throw new AuthorizationFailedException("ATHR-002", "Customer is logged out. Log in again to access this endpoint.");
        }

        final ZonedDateTime now = ZonedDateTime.now();

        if (customerAuthEntity.getExpiresAt().compareTo(now) <= 0) {//Checking accessToken is Expired.
            throw new AuthorizationFailedException("ATHR-003", "Your session is expired. Log in again to access this endpoint.");
        }
        return customerAuthEntity.getCustomer();
    }


}