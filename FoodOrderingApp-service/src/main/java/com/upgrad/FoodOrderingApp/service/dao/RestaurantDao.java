package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.RestaurantEntity;

import java.util.List;

public interface RestaurantDao {

  public List<RestaurantEntity> getAllRestaurants();

  public List<RestaurantEntity> getRestaurantByName(final String restaurantName);

  public RestaurantEntity getRestaurantByUuid(final String restaurantId);

  public RestaurantEntity updateRestaurantRating(RestaurantEntity restaurantEntity);
}
