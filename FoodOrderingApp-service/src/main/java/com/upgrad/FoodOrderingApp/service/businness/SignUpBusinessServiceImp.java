package com.upgrad.FoodOrderingApp.service.businness;

import com.upgrad.FoodOrderingApp.service.dao.CustomerDao;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import com.upgrad.FoodOrderingApp.service.exception.SignUpRestrictedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SignUpBusinessServiceImp implements SignUpBusinessService {

    @Autowired private CustomerDao customerDao;

    @Autowired PasswordCryptographyProvider cryptographyProvider;

    @Transactional(propagation = Propagation.REQUIRED)
    public CustomerEntity signup(CustomerEntity customerEntity) throws SignUpRestrictedException {
        String regex = "^\\d{10}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(customerEntity.getContactNumber());

        if (customerEntity.getContactNumber().length() != 10 && !m.matches()) {
            throw new SignUpRestrictedException("SGR-003", "Invalid contact number!");
        }

        String password = customerEntity.getPassword();
        if (passwordStrength(password)) {
            String[] encryptedText = cryptographyProvider.encrypt(password);
            customerEntity.setPassword(encryptedText[1]);
            customerEntity.setSalt(encryptedText[0]);
        }
        return customerDao.saveCustomer(customerEntity);
    }

    public boolean passwordStrength(String password) throws SignUpRestrictedException {
        String regex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#@$%&*!^]).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            throw new SignUpRestrictedException("SGR-004", "Weak password!");
        } else return true;
    }
}
