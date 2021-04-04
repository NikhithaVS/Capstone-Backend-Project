package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.CustomerAuthEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class AuthTokenDaoImp implements AuthTokenDao{

    @PersistenceContext
    private EntityManager entityManager;

    public CustomerAuthEntity updateToken(CustomerAuthEntity customerAuthEntity){
      entityManager.merge(customerAuthEntity);
      return customerAuthEntity;
    }


    public CustomerAuthEntity createAuthToken(final CustomerAuthEntity customerAuthEntity) {
        entityManager.persist(customerAuthEntity);
        return customerAuthEntity;
    }

    public CustomerAuthEntity getCustomerAuthToken(final String accessToken) {
        try {
            return entityManager
                    .createNamedQuery("getCustomerAuthToken", CustomerAuthEntity.class)
                    .setParameter("accessToken", accessToken)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

}
