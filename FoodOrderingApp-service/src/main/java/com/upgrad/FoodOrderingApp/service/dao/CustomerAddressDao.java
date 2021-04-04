package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class CustomerAddressDao {

    @PersistenceContext
    private EntityManager entityManager;

    //To get Customer Address By Address if no results return null
    public CustomerAddressEntity getCustomerAddressByAddress(AddressEntity addressEntity){
        try {
            CustomerAddressEntity customerAddressEntity = entityManager.createNamedQuery("getCustomerAddressByAddress",CustomerAddressEntity.class).setParameter("address_entity",addressEntity).getSingleResult();
            return customerAddressEntity;
        }catch (NoResultException nre){
            return null;
        }
    }
}
