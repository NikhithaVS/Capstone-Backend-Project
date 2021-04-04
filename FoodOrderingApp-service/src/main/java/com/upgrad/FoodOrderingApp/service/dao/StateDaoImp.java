package com.upgrad.FoodOrderingApp.service.dao;


import com.upgrad.FoodOrderingApp.service.entity.StateEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class StateDaoImp implements StateDao {

    @PersistenceContext
    EntityManager entityManager;

    public StateEntity getStateByUuid(final String uuid) {
        try {
            return entityManager.createNamedQuery("getStateByUUID", StateEntity.class).setParameter("uuid", uuid).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public List<StateEntity> getAllStates() {
        try {
            List<StateEntity> states =
                    entityManager.createNamedQuery("getAllStates", StateEntity.class).getResultList();
            return states;
        } catch (NoResultException nre) {
            return null;
        }
    }

}