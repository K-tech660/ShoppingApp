package com.first.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.first.entity.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
    Optional<Coupon> findByCouponCodeAndUserIdAndAppliedFalse(String couponCode, Long userId);
}
