package com.upgrad.FoodOrderingApp.service.businness;

import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;

public interface CustomerService {

    public CustomerEntity saveCustomer(CustomerEntity customer);
    public CustomerEntity getCustomerByContactNumber(String contactNumber);
    public CustomerEntity updateCustomer(CustomerEntity savedCustomer);
}
