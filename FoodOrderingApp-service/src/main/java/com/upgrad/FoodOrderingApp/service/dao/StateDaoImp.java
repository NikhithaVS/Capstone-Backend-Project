package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.StateEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class StateDaoImp implements StateDao {

  @PersistenceContext private EntityManager entityManager;

  /**
   * @param stateUuid
   * @return StateEntity
   */
  public StateEntity getStateByUuid(final String stateUuid) {
    try {
      return entityManager
          .createNamedQuery("getStateByUuid", StateEntity.class)
          .setParameter("stateUuid", stateUuid)
          .getSingleResult();
    } catch (NoResultException nre) {
      return null;
    }
  }

  /** @return List<StateEntity> */
  public List<StateEntity> getAllStates() {
    try {
      return entityManager.createNamedQuery("getAllStates", StateEntity.class).getResultList();
    } catch (NoResultException nre) {
      return null;
    }
  }
}
