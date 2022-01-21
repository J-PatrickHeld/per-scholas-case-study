package com.jamesheld.oboestore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jamesheld.oboestore.entity.Order;
import com.jamesheld.oboestore.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired //if Spring bean has only one constructor method, can omit @Autowired annotation (but I'm choosing to use it here for practice)
	private OrderRepository orderRepository;
	
	@Override
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}
	
	@Override
	public Order saveOrder(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public Order getOrderById(Long id) {
		return orderRepository.findById(id).get();
	}

	@Override
	public Order updateOrder(Order order) {
		return orderRepository.save(order);
	}
	
	@Override
	public void deleteOrderById(Long id) {
		orderRepository.deleteById(id);
	}
	
	@Override
	public List<Order> getOrdersByEmail(String email) {
		return orderRepository.findByEmail(email);
	}
}
