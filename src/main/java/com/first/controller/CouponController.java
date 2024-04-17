package com.first.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Coupon>> getCoupons() {
        List<Coupon> coupons;
        try {
            coupons = couponService.getAllCoupons();
            return ResponseEntity.ok(coupons);
        } catch (Exception e) {
            e.printStackTrace(); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}