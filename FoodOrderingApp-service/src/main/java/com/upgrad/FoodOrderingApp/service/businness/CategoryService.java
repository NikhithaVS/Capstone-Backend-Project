package com.upgrad.FoodOrderingApp.service.businness;

import com.upgrad.FoodOrderingApp.service.entity.CategoryEntity;
import com.upgrad.FoodOrderingApp.service.exception.CategoryNotFoundException;

import java.util.List;

public interface CategoryService {

  public List<CategoryEntity> getAllCategoriesOrderedByName();

  public CategoryEntity getCategoryById(final String categoryUuid) throws CategoryNotFoundException;

  public List<CategoryEntity> getCategoriesByRestaurant(String restaurantId);
}
