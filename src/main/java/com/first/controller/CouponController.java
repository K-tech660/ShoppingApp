package com.first.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.first.Service.CouponService;
import com.first.entity.Coupon;

@RestController
@RequestMapping("/api")
public class CouponController {

	@Autowired
	private CouponService couponService;

	
	@GetMapping("/fetchCoupons")
	public List<Coupon> getCoupons() {
	
		return couponService.getAllCoupons();
	}
}