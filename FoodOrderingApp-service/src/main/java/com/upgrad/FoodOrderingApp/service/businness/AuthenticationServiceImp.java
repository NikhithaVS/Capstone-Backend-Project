package com.upgrad.FoodOrderingApp.service.businness;

import com.upgrad.FoodOrderingApp.service.dao.AuthTokenDao;
import com.upgrad.FoodOrderingApp.service.dao.CustomerDao;
import com.upgrad.FoodOrderingApp.service.dao.CustomerDaoImp;
import com.upgrad.FoodOrderingApp.service.entity.CustomerAuthEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import com.upgrad.FoodOrderingApp.service.exception.AuthenticationFailedException;
import com.upgrad.FoodOrderingApp.service.exception.AuthorizationFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.UUID;

@Service
public class AuthenticationServiceImp implements AuthenticationService {
    @Autowired private CustomerDao customerDao;

    @Autowired private PasswordCryptographyProvider cryptographyProvider;

    @Autowired
    AuthTokenDao authTokenDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public CustomerAuthEntity authenticate(final String contactNumber, final String password)
            throws AuthenticationFailedException {
        CustomerEntity customerEntity = customerDao.getCustomerByContactNumber(contactNumber);
        if (customerEntity == null) {
            throw new AuthenticationFailedException(
                    "ATH-001", "This contact number has not been registered!");
        }
        final String encryptedPassword =
                cryptographyProvider.encrypt(password, customerEntity.getSalt());
        if (encryptedPassword.equals(customerEntity.getPassword())) {
            JwtTokenProvider jwtTokenProvider = new JwtTokenProvider(encryptedPassword);
            CustomerAuthEntity newCustomerAuthEntity = new CustomerAuthEntity();
            newCustomerAuthEntity.setCustomer(customerEntity);
            newCustomerAuthEntity.setUuid(UUID.randomUUID().toString());
            final ZonedDateTime now = ZonedDateTime.now();
            final ZonedDateTime expiresAt = now.plusHours(8);
            newCustomerAuthEntity.setAccessToken(
                    jwtTokenProvider.generateToken(customerEntity.getUuid(), now, expiresAt));
            newCustomerAuthEntity.setLoginAt(now);
            newCustomerAuthEntity.setExpiresAt(expiresAt);
            authTokenDao.createAuthToken(newCustomerAuthEntity);

            return newCustomerAuthEntity;
        } else {
            throw new AuthenticationFailedException("ATH-002", "Invalid Credentials");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public CustomerAuthEntity validateToken(String authorisation)
            throws AuthorizationFailedException {
        CustomerAuthEntity customerAuthEntity = authTokenDao.getCustomerAuthToken(authorisation);
        System.out.println(customerAuthEntity.getLoginAt());
        if (customerAuthEntity == null) {
            throw new AuthorizationFailedException("ATHR-001", "Customer is not Logged in.");
        } else if (customerAuthEntity.getLogoutAt()!=null) {
            throw new AuthorizationFailedException(
                    "ATHR-002", "Customer is logged out. Log in again to access this endpoint.");
        } else if (customerAuthEntity.getExpiresAt().isBefore(ZonedDateTime.now())) {
            throw new AuthorizationFailedException(
                    "ATHR-003", "Your session is expired. Log in again to access this endpoint.");
        }
        return customerAuthEntity;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public CustomerAuthEntity updateCustomerToken(CustomerAuthEntity customerAuthEntity){
        return authTokenDao.updateToken(customerAuthEntity);
    }

}
