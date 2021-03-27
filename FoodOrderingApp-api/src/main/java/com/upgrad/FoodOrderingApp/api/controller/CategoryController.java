package com.upgrad.FoodOrderingApp.api.controller;

import com.upgrad.FoodOrderingApp.api.model.CategoryDetailsResponse;
import com.upgrad.FoodOrderingApp.api.model.CategoryListResponse;
import com.upgrad.FoodOrderingApp.service.exception.CategoryNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/")
public class CategoryController {

  @RequestMapping(
      method = RequestMethod.GET,
      path = "/category",
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<CategoryListResponse> getAllCategories() {
    return null;
  }

  @RequestMapping(
      method = RequestMethod.GET,
      path = "/category/{category_id}",
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<CategoryDetailsResponse> getCategoryById(
      @PathVariable("category_id") final String categoryId) throws CategoryNotFoundException {
    return null;
  }
}
