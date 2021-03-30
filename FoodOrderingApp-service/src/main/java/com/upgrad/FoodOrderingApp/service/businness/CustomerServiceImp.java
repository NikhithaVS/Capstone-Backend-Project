package com.upgrad.FoodOrderingApp.service.businness;

import com.upgrad.FoodOrderingApp.service.dao.CustomerDao;
import com.upgrad.FoodOrderingApp.service.dao.CustomerDaoImp;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerServiceImp implements CustomerService {

    @Autowired
    CustomerDao customerDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public CustomerEntity saveCustomer(CustomerEntity customer){
        return customerDao.saveCustomer(customer);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public CustomerEntity getCustomerByContactNumber(String contactNumber){
        return customerDao.getCustomerByContactNumber(contactNumber);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public CustomerEntity updateCustomer(CustomerEntity savedCustomer){
        return customerDao.updateCustomer(savedCustomer);
    }
}
