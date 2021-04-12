package com.upgrad.FoodOrderingApp.service.businness;

import com.upgrad.FoodOrderingApp.service.entity.ItemEntity;
import com.upgrad.FoodOrderingApp.service.entity.RestaurantEntity;
import com.upgrad.FoodOrderingApp.service.exception.ItemNotFoundException;

import java.util.List;

public interface ItemService {
    public List<ItemEntity> getItemsByCategoryAndRestaurant(final String restaurantUuid, final String categoryUuid);
    public ItemEntity getItemByUuid(final String itemUuid) throws ItemNotFoundException;
    public List<ItemEntity> getItemsByPopularity(final RestaurantEntity restaurantEntity);

}
