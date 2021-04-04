package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.AddressEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class AddressDaoImp implements AddressDao {

    @PersistenceContext
    EntityManager entityManager;

    public AddressEntity createAddress(AddressEntity addressEntity) {
        entityManager.persist(addressEntity);
        return addressEntity;
    }

    public AddressEntity deleteAddress(AddressEntity address) {
        entityManager.remove(address);
        return address;
    }

    public AddressEntity getAddressByAddressId(String addressID) {
        try {
            return entityManager
                    .createNamedQuery("getByAddressID", AddressEntity.class)
                    .setParameter("addressID", addressID)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
}