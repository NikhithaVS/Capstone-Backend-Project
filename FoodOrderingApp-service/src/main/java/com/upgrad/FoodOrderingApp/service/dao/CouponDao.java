package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.CouponEntity;
import com.upgrad.FoodOrderingApp.service.exception.CouponNotFoundException;

public interface CouponDao {

    public CouponEntity getCouponByCouponName(final String couponName)
            throws CouponNotFoundException;
    public CouponEntity getCouponByCouponUuid(final String couponUuid);
}
