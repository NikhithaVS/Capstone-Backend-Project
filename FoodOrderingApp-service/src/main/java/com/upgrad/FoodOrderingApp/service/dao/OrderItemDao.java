package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.OrderItemEntity;

import java.util.List;

public interface OrderItemDao {

    public List<OrderItemEntity> getOrderItemsByOrderUuid(final String orderUuid);
    public void saveOrderItem(OrderItemEntity orderItemEntity);
}
