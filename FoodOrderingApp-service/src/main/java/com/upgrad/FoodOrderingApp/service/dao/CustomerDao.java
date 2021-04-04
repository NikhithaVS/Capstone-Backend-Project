package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class CustomerDao {


    @PersistenceContext
    private EntityManager entityManager;

    //To get Customer By Uuid if no results return null
    public CustomerEntity getCustomerByUuid (final String uuid){
        try {
            CustomerEntity customer = entityManager.createNamedQuery("customerByUuid",CustomerEntity.class).setParameter("uuid",uuid).getSingleResult();
            return customer;
        }catch (NoResultException nre){
            return null;
        }
    }
}