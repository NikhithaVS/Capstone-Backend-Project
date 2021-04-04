package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.CustomerAuthEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class CustomerDaoImp implements CustomerDao{

  @PersistenceContext EntityManager entityManager;

  public CustomerEntity saveCustomer(CustomerEntity customer){
    entityManager.persist(customer);
    return customer;
  }

  public CustomerEntity getCustomerByContactNumber(String contactNumber) {
    try {
      return entityManager
          .createNamedQuery("getCustomerByContactNumber", CustomerEntity.class)
          .setParameter("contactNumber", contactNumber)
          .getSingleResult();
    } catch (NoResultException nr) {
      return null;
    }
  }

  public CustomerEntity updateCustomer(CustomerEntity customer){
    return entityManager.merge(customer);
  }
}
