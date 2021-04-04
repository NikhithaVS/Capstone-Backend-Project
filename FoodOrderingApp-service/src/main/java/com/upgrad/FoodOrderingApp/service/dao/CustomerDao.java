package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.CustomerAuthEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;

public interface CustomerDao {
    public CustomerEntity saveCustomer(CustomerEntity customer);
    public CustomerEntity getCustomerByContactNumber(String contactNumber);
    public CustomerEntity updateCustomer(CustomerEntity customer);
}
