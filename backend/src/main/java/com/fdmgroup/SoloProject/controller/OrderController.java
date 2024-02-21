package com.fdmgroup.SoloProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.SoloProject.dto.OrderDto;
import com.fdmgroup.SoloProject.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/user/{userId}/create")
	public ResponseEntity<OrderDto> createOrder(@PathVariable Long userId) {
		OrderDto createdOrder = orderService.createOrder(userId);
		return ResponseEntity.ok(createdOrder);
	}

	@GetMapping
	public ResponseEntity<List<OrderDto>> getAllOrders() {
		return ResponseEntity.ok(orderService.findAllOrders());
	}

	@GetMapping("/{orderId}")
	public ResponseEntity<OrderDto> getOrderById(@PathVariable Long orderId) {
		OrderDto order = orderService.findOrderById(orderId);
		return ResponseEntity.ok(order);
	}
}