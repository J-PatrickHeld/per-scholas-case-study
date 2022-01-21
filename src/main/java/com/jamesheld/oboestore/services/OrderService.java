package com.jamesheld.oboestore.services;

import java.util.List;

import com.jamesheld.oboestore.entity.Order;

public interface OrderService {

	List<Order> getAllOrders();
	
	Order saveOrder(Order order);
	
	Order getOrderById(Long id);
	
	Order updateOrder(Order order);
	
	void deleteOrderById(Long id);
	
	List<Order> getOrdersByEmail(String email);
}
