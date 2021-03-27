package com.upgrad.FoodOrderingApp.api.controller;

import com.upgrad.FoodOrderingApp.api.model.ItemList;
import com.upgrad.FoodOrderingApp.service.exception.RestaurantNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/")
public class ItemController {

  @RequestMapping(method = RequestMethod.GET, path = "/item/restaurant/{restaurant_id}",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<List<ItemList>> getTop5ItemsByPopularity(
      @PathVariable("restaurant_id") final String restaurantId)
      throws RestaurantNotFoundException {
    return null;
  }
}
