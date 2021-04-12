package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.PaymentEntity;

import java.util.List;

public interface PaymentDao {
  public List<PaymentEntity> getAllPaymentMethods();

  public PaymentEntity getPaymentByUuid(final String paymentUuid);
}
