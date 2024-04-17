package com.first.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.first.Service.CouponService;
import com.first.Service.OrderService;
import com.first.Service.UserService;
import com.first.entity.Coupon;
import com.first.entity.Inventory;
import com.first.entity.Orders;
import com.first.entity.User;
import com.first.repository.CouponRepository;
import com.first.repository.InventoryRepository;
import com.first.repository.OrderRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private CouponService couponService;
	@Autowired
	private CouponRepository couponRepository;

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
    private UserService userService;

	@Autowired
	private InventoryRepository inventoryRepository;

	 @Override
	 @Transactional
	 public Orders placeOrder(Long userId, int quantity, String couponCode) {
	     try {
	         // Retrieve user details
	         User user = userService.getUserById(userId);

	         if (user == null) {
	             throw new RuntimeException("User not found. Cannot place order.");
	         }

	         // Retrieve inventory (assuming inventory ID is provided dynamically)
	         Inventory inventory = inventoryRepository.findById(1L)
	                 .orElseThrow(() -> new RuntimeException("Inventory not found"));

	         if (inventory.getAvailable() < quantity) {
	             throw new RuntimeException("Insufficient quantity available in inventory. Order cannot be placed.");
	         }

	         // Calculate order amount
	         int unitPrice = inventory.getPrice();
	         int orderAmount = quantity * unitPrice;

	         // Apply coupon if valid
	         if (couponCode != null && !couponCode.isEmpty()) {
	             double discountPercentage = couponService.applyCoupon(userId, couponCode);
	             if (discountPercentage > 0) {
	                 orderAmount = (int) (orderAmount * (1.0 - (discountPercentage / 100.0)));
	             }
	         }

	         // Create new order
	         Orders order = new Orders();
	         order.setUser(user);
	         order.setQuantity(quantity);
	         order.setAmount(orderAmount);
	         order.setStatus("PLACED");
	         order.setTransactionId(generateTransactionId());
	         order.setDate(new Date());

	         // Check and set coupon if provided
	         if (couponCode != null && !couponCode.isEmpty()) {
	             Coupon coupon = couponRepository.findByCouponCode(couponCode);
	             if (coupon != null) {
	                 order.setCoupon(coupon);
	             }
	             else {
	            	 order.setCoupon(null);
	             }
	         }

	         orderRepository.save(order);

	         // Update inventory
	         inventory.setOrdered(inventory.getOrdered() + quantity);
	         inventory.setAvailable(inventory.getAvailable() - quantity);
	         inventoryRepository.save(inventory);

	         return order; // Return the placed order
	     } catch (RuntimeException ex) {
	         // Log the exception or perform other error handling tasks
	         throw new RuntimeException("Failed to place order: " + ex.getMessage());
	     }
	 }

	 
	 private String generateTransactionId() {
	     return UUID.randomUUID().toString();
	 }
	


		@Override
		public void processPayment(Long orderId, int amount) {
		    // Retrieve the order by ID
		    Orders order = orderRepository.findById(orderId)
		            .orElseThrow(() -> new RuntimeException("Order not found"));

		    // Retrieve the user associated with the order
		    User user = order.getUser();

		    // Check if the user is null or invalid
		    if (user == null || user.getId() == null) {
		        throw new RuntimeException("User not found or invalid for this order. Cannot process payment.");
		    }

		    // Ensure that the order status is eligible for payment processing (e.g., "PLACED")
		    if (!order.getStatus().equals("PLACED")) {
		        throw new RuntimeException("Cannot process payment for this order. Order status is not valid.");
		    }

		    // Simulate payment processing (mock implementation)
		    if (amount <= 0) {
		        throw new RuntimeException("Invalid payment amount. Payment cannot be processed.");
		    }

		
		    if (amount==order.getAmount()) {
		        order.setStatus("SUCCESS");
		    } else {
		        order.setStatus("PLACED");
		    }

		    // Save the updated order status
		    orderRepository.save(order);
		}
	@Override
	public List<Orders> getAllOrder() {
		
		
		return orderRepository.findAll();
	}

}