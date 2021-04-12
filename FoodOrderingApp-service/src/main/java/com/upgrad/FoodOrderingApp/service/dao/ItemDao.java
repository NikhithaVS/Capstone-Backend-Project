package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.ItemEntity;

public interface ItemDao {
    public ItemEntity getItemByUuid(final String itemUuid);
}
