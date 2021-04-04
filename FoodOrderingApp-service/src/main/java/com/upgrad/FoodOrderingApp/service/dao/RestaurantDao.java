package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.RestaurantEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RestaurantDao {

    @PersistenceContext
    private EntityManager entityManager;

    //To get restaurant by UUID from db
    public RestaurantEntity getRestaurantByUuid(String uuid) {
        try {
            RestaurantEntity restaurantEntity = entityManager.createNamedQuery("getRestaurantByUuid",RestaurantEntity.class).setParameter("uuid",uuid).getSingleResult();
            return restaurantEntity;
        }catch (NoResultException nre){
            return null;
        }

    }
}