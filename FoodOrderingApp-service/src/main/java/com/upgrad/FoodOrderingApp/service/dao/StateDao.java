package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.StateEntity;

import java.util.List;

public interface StateDao {
    public StateEntity getStateByUuid(final String uuid);
    public List<StateEntity> getAllStates();
}
