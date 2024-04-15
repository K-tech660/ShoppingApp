package com.first.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import com.first.Service.CouponService;
import com.first.Service.OrderService;
import com.first.entity.Coupon;
import com.first.entity.Orders;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private CouponService couponService;

	@PostMapping("/{userId}/place")
	public Orders placeOrder(@PathVariable Long userId, @RequestParam int quantity,
			@RequestParam(required = false) String couponCode) {
		return orderService.placeOrder(userId, quantity, couponCode);
	}

	@GetMapping("/fetchCoupons")
	public List<Coupon> getCoupons() {

		return couponService.getAllCoupons();
	}

	@PostMapping("/{userId}/{orderId}/pay")
	public void processPayment(@PathVariable Long userId, @PathVariable Long orderId, @RequestParam int amount) {
		orderService.processPayment(userId, orderId, amount);
	}

	@GetMapping("/orders")
	public List<Orders> getOrder() {

		return orderService.getAllOrder();
	}

}