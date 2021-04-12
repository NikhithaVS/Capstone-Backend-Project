package com.upgrad.FoodOrderingApp.service.businness;

import com.upgrad.FoodOrderingApp.service.entity.CouponEntity;
import com.upgrad.FoodOrderingApp.service.entity.OrderEntity;
import com.upgrad.FoodOrderingApp.service.entity.OrderItemEntity;
import com.upgrad.FoodOrderingApp.service.exception.*;

import java.util.List;

public interface OrderService {
  public OrderEntity saveOrder(OrderEntity orderEntity)
      throws CouponNotFoundException, AddressNotFoundException, PaymentMethodNotFoundException,
          RestaurantNotFoundException, AuthorizationFailedException;

  public CouponEntity getCouponByCouponName(final String couponName) throws CouponNotFoundException;

  public List<OrderEntity> getOrdersByCustomers(final String customerUuid);

  public CouponEntity getCouponByCouponId(final String couponId) throws CouponNotFoundException;

  public OrderItemEntity saveOrderItem(OrderItemEntity orderItemEntity);
}
