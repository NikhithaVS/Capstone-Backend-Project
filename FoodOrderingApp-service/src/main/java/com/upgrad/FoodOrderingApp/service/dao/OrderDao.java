package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.OrderEntity;

import java.util.List;

public interface OrderDao {

    public List<OrderEntity> getAllOrdersRestaurantUuid(final String restaurantUuid);
    public List<OrderEntity> getAllOrdersOfCustomerByUuid(final String customerUuid);
    public OrderEntity saveOrder(OrderEntity order);
}
