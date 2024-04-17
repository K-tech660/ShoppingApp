package com.first.Service;

import java.util.List;

import com.first.entity.Coupon;

public interface CouponService {

	

	List<Coupon> getAllCoupons();

	double applyCoupon(Long userId, String couponCode);

}