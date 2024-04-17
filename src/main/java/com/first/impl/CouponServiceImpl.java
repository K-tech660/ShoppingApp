package com.first.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.first.Service.CouponService;
import com.first.entity.Coupon;
import com.first.repository.CouponRepository;

@Service
public class CouponServiceImpl implements CouponService {

	@Autowired
	private CouponRepository couponRepository;

	@Override
	public List<Coupon> getAllCoupons() {

		return couponRepository.findAll();
	}

	@Override
    public double applyCoupon(Long userId, String couponCode) {
        try {
            Optional<Coupon> optionalCoupon = couponRepository.findByCouponCodeAndUserIdAndAppliedFalse(couponCode, userId);

            if (optionalCoupon.isPresent()) {
                Coupon coupon = optionalCoupon.get();
                double discountPercentage = coupon.getDiscountPercentage();

                // Mark coupon as applied
                coupon.setApplied(true);
                couponRepository.save(coupon);

                return discountPercentage;
            }

            // Coupon not found or already applied
            return 0.0;
        } catch (Exception ex) {
            // Log the exception or perform other error handling tasks
            throw new RuntimeException("Failed to apply coupon: " + ex.getMessage());
        }
    }
}

