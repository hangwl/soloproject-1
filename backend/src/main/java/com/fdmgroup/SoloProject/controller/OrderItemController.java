package com.fdmgroup.SoloProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.SoloProject.dto.OrderItemDto;
import com.fdmgroup.SoloProject.service.OrderItemService;

@RestController
@RequestMapping("/api/orderItems")
public class OrderItemController {

	@Autowired
	private OrderItemService orderItemService;

	@GetMapping("/order/{orderId}")
	public ResponseEntity<List<OrderItemDto>> getOrderItemsByOrderId(@PathVariable Long orderId) {
		List<OrderItemDto> orderItems = orderItemService.findOrderItemsByOrderId(orderId);
		return ResponseEntity.ok(orderItems);
	}

	@GetMapping("/{orderItemId}")
	public ResponseEntity<OrderItemDto> getOrderItemById(@PathVariable Long orderItemId) {
		OrderItemDto orderItem = orderItemService.findOrderItemById(orderItemId);
		return ResponseEntity.ok(orderItem);
	}

}