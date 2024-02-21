package com.fdmgroup.SoloProject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.SoloProject.dto.OrderItemDto;
import com.fdmgroup.SoloProject.model.OrderItem;
import com.fdmgroup.SoloProject.repository.OrderItemRepository;

@Service
public class OrderItemService {

	@Autowired
	private OrderItemRepository orderItemRepository;

	// Retrieves all order items associated with a specific order ID
	public List<OrderItemDto> findOrderItemsByOrderId(Long orderId) {
		return orderItemRepository.findByOrder_OrderId(orderId).stream().map(this::convertToDto)
				.collect(Collectors.toList());
	}

	// Finds a specific order item by its ID and returns it as OrderItemDto
	public OrderItemDto findOrderItemById(Long orderItemId) {
		OrderItem orderItem = orderItemRepository.findById(orderItemId)
				.orElseThrow(() -> new RuntimeException("OrderItem not found with ID: " + orderItemId));
		return convertToDto(orderItem);
	}

	// Helper method to convert OrderItem entity to OrderItemDto
	private OrderItemDto convertToDto(OrderItem orderItem) {
		return new OrderItemDto(orderItem.getOrderItemId(), orderItem.getOrder().getOrderId(),
				orderItem.getProduct().getProductId(), orderItem.getQuantity());
	}
}
