package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.CategoryEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CategoryDaoImp implements CategoryDao {
  @PersistenceContext private EntityManager entityManager;

  /** @return List of all category entities */
  public List<CategoryEntity> getAllCategoriesOrderedByName() {
    try {
      return entityManager
          .createNamedQuery("getAllCategoriesOrderedByName", CategoryEntity.class)
          .getResultList();
    } catch (NoResultException nre) {
      return null;
    }
  }

  /** @return Get category details */
  public CategoryEntity getCategoryById(final String categoryUuid) {
    try {
      return entityManager
          .createNamedQuery("getCategoryByUuid", CategoryEntity.class)
          .setParameter("categoryUuid", categoryUuid)
          .getSingleResult();
    } catch (NoResultException nre) {
      return null;
    }
  }
}
