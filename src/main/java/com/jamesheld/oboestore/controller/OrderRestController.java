package com.jamesheld.oboestore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jamesheld.oboestore.entity.Order;
import com.jamesheld.oboestore.services.OrderService;

@ComponentScan
@EnableAutoConfiguration
@RestController
@RequestMapping("/api")
public class OrderRestController {

	@Autowired
	private OrderService orderService;
	
	@GetMapping("/orders")
	@ResponseBody
	public List<Order> getAllOrders() {
		return orderService.getAllOrders();
	}
	
	@GetMapping("/orders/{id}")
	public Order getOrderById(@PathVariable("id") Long id) {
		return orderService.getOrderById(id);
	}
	
	@PostMapping("/orders")
	public Order createOrder(@RequestBody Order order) {
		orderService.saveOrder(order);
		return order;
	}
	
	@PutMapping("/orders/{id}")
	public Order updateOrder(@PathVariable("id") Long id, @RequestBody Order order) {
		
		//get order from database by id
		Order existingOrder = orderService.getOrderById(id);
		existingOrder.setId(id);
		existingOrder.setFirstName(order.getFirstName());
		existingOrder.setLastName(order.getLastName());
		existingOrder.setEmail(order.getEmail());
		existingOrder.setAddress(order.getAddress());
		existingOrder.setCity(order.getCity());
		existingOrder.setState(order.getState());
		existingOrder.setZipCode(order.getZipCode());
		existingOrder.setNoOfReeds(order.getNoOfReeds());
		
		//save updated order object
		orderService.updateOrder(existingOrder);
		
		return existingOrder;
	}
	
	@DeleteMapping("orders/{id}")
	public void deleteOrder(@PathVariable("id") Long id) {
		orderService.deleteOrderById(id);
	}
}
