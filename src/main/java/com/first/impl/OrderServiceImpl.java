package com.first.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.first.Service.CouponService;
import com.first.Service.OrderService;

import com.first.entity.Inventory;
import com.first.entity.Orders;

import com.first.repository.InventoryRepository;
import com.first.repository.OrderRepository;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private CouponService couponService;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private InventoryRepository inventoryRepository;

	@Override
	@Transactional
	public Orders placeOrder(Long userId, int quantity, String couponCode) {
		// Retrieve inventory (assuming inventory ID is 1)
		Inventory inventory = inventoryRepository.findById(1L)
				.orElseThrow(() -> new RuntimeException("Inventory not found"));

		// Check if requested quantity is available
		if (inventory.getAvailable() < quantity) {
			throw new RuntimeException("Insufficient quantity available in inventory");
		}

		// Calculate order amount (assuming fixed price per unit)
		int unitPrice = inventory.getPrice();
		int orderAmount = quantity * unitPrice;

		// Apply coupon if valid
		double discountPercentage = couponService.applyCoupon(userId, couponCode);
		if (discountPercentage > 0) {
			orderAmount = (int) (orderAmount * (1.0 - (discountPercentage / 100.0)));
		}

		// Create new order
		Orders order = new Orders();
		order.setUserId(userId);
		order.setQuantity(quantity);
		order.setAmount(orderAmount);
		order.setCoupon(couponCode);
		order.setStatus("PLACED");
		order.setTransactionId(generateTransactionId());
		order.setDate(new Date());

		// Save order
		orderRepository.save(order);

		// Update inventory
		inventory.setOrdered(inventory.getOrdered() + quantity);
		inventory.setAvailable(inventory.getAvailable() - quantity);
		inventoryRepository.save(inventory);

		return order;
	}

	private String generateTransactionId() {
		// Implement a method to generate a transaction ID (mock implementation)
		return "TXN_" + System.currentTimeMillis();
	}

	

	@Override
	public void processPayment(Long userId, Long orderId, int amount) {
		// Implement payment processing logic (mock implementation)
		// Simulating payment success/failure based on amount and order details
		if (amount <= 0) {
			throw new RuntimeException("Invalid payment amount");
		}

		Orders order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));

		if (!order.getUserId().equals(userId)) {
			throw new RuntimeException("Order does not belong to the user");
		}

		if (!order.getStatus().equals("PLACED")) {
			throw new RuntimeException("Order is already paid or cancelled");
		}

		// Mocking payment status randomly
		double random = Math.random();
		if (random < 0.8) {
			order.setStatus("SUCCESS");
		} else {
			order.setStatus("FAILED");
		}
		orderRepository.save(order);

	}
	@Override
	public List<Orders> getAllOrder() {
		
		
		return orderRepository.findAll();
	}

}