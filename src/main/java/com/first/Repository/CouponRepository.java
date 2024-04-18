package com.first.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.first.entity.Coupon;


@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
<<<<<<< HEAD

    Optional<Coupon> findByCouponCodeAndUserIdAndAppliedFalse(String couponCode, Long userId);

	
	Coupon findByCouponCode(String couponCode);
}
=======
    Optional<Coupon> findByCouponCodeAndUserIdAndAppliedFalse(String couponCode, Long userId);
}
>>>>>>> 087f67a31da8a57b5b8a9a24ea25a0380858454d
