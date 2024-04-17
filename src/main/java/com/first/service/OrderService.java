package com.first.Service;

import java.util.List;

import com.first.entity.Orders;

public interface OrderService {
	Orders placeOrder(Long userId, int quantity, String couponCode);

	void processPayment(Long orderId, int amount);

	List<Orders> getAllOrder();
	

	
}
