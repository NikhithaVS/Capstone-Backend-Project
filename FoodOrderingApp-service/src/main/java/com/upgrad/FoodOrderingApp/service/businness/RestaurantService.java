package com.upgrad.FoodOrderingApp.service.businness;

import com.upgrad.FoodOrderingApp.service.dao.*;
import com.upgrad.FoodOrderingApp.service.entity.*;
import com.upgrad.FoodOrderingApp.service.exception.RestaurantNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {

    @Autowired
    RestaurantDao restaurantDao; //Handles all data related to the RestaurantEntity

    /* This method is to get restaurant By UUID and returns RestaurantEntity. its takes restaurantUuid as the input string.
         If error throws exception with error code and error message.
         */
    public RestaurantEntity restaurantByUUID(String restaurantUuid) throws RestaurantNotFoundException {
        if (restaurantUuid == null || restaurantUuid == "") { //Checking for restaurantUuid to be null or empty to throw exception.
            throw new RestaurantNotFoundException("RNF-002", "Restaurant id field should not be empty");
        }

        //Calls getRestaurantByUuid of restaurantDao to get the  RestaurantEntity
        RestaurantEntity restaurantEntity = restaurantDao.getRestaurantByUuid(restaurantUuid);

        if (restaurantEntity == null) { //Checking for restaurantEntity to be null or empty to throw exception.
            throw new RestaurantNotFoundException("RNF-001", "No restaurant by this id");
        }

        return restaurantEntity;


    }
}