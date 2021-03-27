package com.upgrad.FoodOrderingApp.api.controller;

import com.upgrad.FoodOrderingApp.api.model.RestaurantDetailsResponse;
import com.upgrad.FoodOrderingApp.api.model.RestaurantListResponse;
import com.upgrad.FoodOrderingApp.api.model.RestaurantUpdatedResponse;
import com.upgrad.FoodOrderingApp.service.exception.AuthorizationFailedException;
import com.upgrad.FoodOrderingApp.service.exception.CategoryNotFoundException;
import com.upgrad.FoodOrderingApp.service.exception.InvalidRatingException;
import com.upgrad.FoodOrderingApp.service.exception.RestaurantNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/")
public class RestaurantController {

  @RequestMapping(
      method = RequestMethod.GET,
      path = "/restaurant",
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<RestaurantListResponse> getAllRestaurants() {
    return null;
  }

  @RequestMapping(
      method = RequestMethod.GET,
      path = "/restaurant/name/{restaurant_name}",
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<RestaurantListResponse> getRestaurantsByName(
      @PathVariable("restaurant_name") final String restaurantName)
      throws RestaurantNotFoundException {
    return null;
  }

  @RequestMapping(
      method = RequestMethod.GET,
      path = "/restaurant/category/{category_id}",
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<RestaurantListResponse> getRestaurantsByCategoryId(
      @PathVariable("category_id") final String categoryId) throws CategoryNotFoundException {
    return null;
  }

  @RequestMapping(
      method = RequestMethod.GET,
      path = "/api/restaurant/{restaurant_id}",
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<RestaurantDetailsResponse> getRestaurantByRestaurantID(
      @PathVariable("restaurant_id") final String restaurantId) throws RestaurantNotFoundException {
    return null;
  }

  @RequestMapping(
      method = RequestMethod.PUT,
      path = "/api/restaurant/{restaurant_id}",
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<RestaurantUpdatedResponse> updateRestaurantDetails(
      @PathVariable("restaurant_id") final String restaurantId,
      @RequestParam("customer_rating") final Double customerRating,
      @RequestHeader("authorisation") final String authorisation)
      throws AuthorizationFailedException, RestaurantNotFoundException, InvalidRatingException {
    return null;
  }


}
