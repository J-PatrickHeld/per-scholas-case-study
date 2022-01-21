package com.jamesheld.oboestore.controller;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.jamesheld.oboestore.entity.Order;
import com.jamesheld.oboestore.services.OrderService;

@Controller
public class OrderController {
	
	@Autowired //if Spring bean has only one constructor method, can omit @Autowired annotation (but I'm choosing to use it here for practice)
	private OrderService orderService;
	
	//handler method to handle list orders and return model and view
	@GetMapping("/orders")
	public String listOrders(Model model) {
		List<Order> orders = orderService.getAllOrders();
		Set<String> emails = new LinkedHashSet<>();
		for (Order order: orders) {
			emails.add(order.getEmail());
		}
		model.addAttribute("orders", orders);
		model.addAttribute("emails", emails);
		return "orders";
	}
	
	@GetMapping("/orders/email/{email}")
	public String getOrdersByEmail(@PathVariable String email, Model model) {
		model.addAttribute("orders", orderService.getOrdersByEmail(email));
		return "orders";
	}
	
	@GetMapping("/orders/new")
	public String createOrderForm(Model model) {
		Order order = new Order();
		model.addAttribute("order", order);
		return "create_order";
	}
	
	@PostMapping("/orders/save")
	public String saveOrder(@ModelAttribute("order") Order order) { //bind attribute "order" from model to method parameter
		orderService.saveOrder(order);
		return "redirect:/orders/new?success";
	}
	
	@GetMapping("/orders/edit/{id}")
	public String editOrderForm(@PathVariable Long id, Model model) { //bind path variable "{id}" FROM url TO method parameter
		model.addAttribute("order", orderService.getOrderById(id));
		return "edit_order";
	}
	
	@PostMapping("/orders/update/{id}")
	public String updateOrder(@PathVariable Long id, @ModelAttribute("order") Order order, Model model) {
		
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
		return "redirect:/orders";
	}
	
	//handler method to handle delete student request
	@GetMapping("/orders/delete/{id}")
	public String deleteOrder(@PathVariable Long id) {
		orderService.deleteOrderById(id);
		return "redirect:/orders";
	}
	
	@GetMapping("/403")
	public String error403() {
		return "403";
	}
	
}
