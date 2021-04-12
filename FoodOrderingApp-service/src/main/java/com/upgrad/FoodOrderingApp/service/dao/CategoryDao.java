package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.CategoryEntity;

import java.util.List;

public interface CategoryDao {
    public List<CategoryEntity> getAllCategoriesOrderedByName();
    public CategoryEntity getCategoryById(final String categoryUuid);
}
