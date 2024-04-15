package com.first.Service;

import java.util.List;

import com.first.entity.Orders;

public interface OrderService {
	Orders placeOrder(Long userId, int quantity, String couponCode);

	void processPayment(Long userId, Long orderId, int amount);

	List<Orders> getAllOrder();
	// TODO Auto-generated method stub

	
}
