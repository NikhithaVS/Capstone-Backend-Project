package com.upgrad.FoodOrderingApp.service.businness;

import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import com.upgrad.FoodOrderingApp.service.exception.SignUpRestrictedException;

public interface SignUpBusinessService {
    public CustomerEntity signup(CustomerEntity customerEntity) throws SignUpRestrictedException;
    public boolean passwordStrength(String password) throws SignUpRestrictedException;
}
