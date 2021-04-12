package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.PaymentEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PaymentDaoImp implements PaymentDao{

  @PersistenceContext private EntityManager entityManager;

  /**
   * @param paymentUuid
   * @return
   */
  public PaymentEntity getPaymentByUuid(final String paymentUuid) {
    try {
      return entityManager
          .createNamedQuery("paymentByUuid", PaymentEntity.class)
          .setParameter("uuid", paymentUuid)
          .getSingleResult();
    } catch (NoResultException nre) {
      return null;
    }
  }

  /** @return List<PaymentEntity> */
  public List<PaymentEntity> getAllPaymentMethods() {
    try {
      return entityManager.createNamedQuery("allPayments", PaymentEntity.class).getResultList();
    } catch (NoResultException nre) {
      return null;
    }
  }
}
