package com.first.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Orders> placeOrder(@PathVariable Long userId,
                                             @RequestParam int quantity,
                                             @RequestParam(required = false) String couponCode) {
        try {
            Orders order = orderService.placeOrder(userId, quantity, couponCode);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/fetchCoupons")
    public ResponseEntity<List<Coupon>> getCoupons() {
        try {
            List<Coupon> coupons = couponService.getAllCoupons();
            return ResponseEntity.ok(coupons);
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/{userId}/{orderId}/pay")
    public ResponseEntity<String> processPayment(@PathVariable Long userId,
                                                 @PathVariable Long orderId,
                                                 @RequestParam int amount) {
        try {
            orderService.processPayment( orderId, amount);
            Orders order=new Orders();
            return ResponseEntity.ok(order.getStatus());
        } catch (Exception e) {
            e.printStackTrace(); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Payment processing failed");
        }
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Orders>> getAllOrders() {
        try {
            List<Orders> orders = orderService.getAllOrder();
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            e.printStackTrace(); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}