package com.fdmgroup.SoloProject.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.SoloProject.dto.OrderDto;
import com.fdmgroup.SoloProject.dto.OrderItemDto;
import com.fdmgroup.SoloProject.model.Cart;
import com.fdmgroup.SoloProject.model.CartItem;
import com.fdmgroup.SoloProject.model.OrderEntity;
import com.fdmgroup.SoloProject.model.OrderItem;
import com.fdmgroup.SoloProject.model.User;
import com.fdmgroup.SoloProject.repository.CartRepository;
import com.fdmgroup.SoloProject.repository.OrderRepository;
import com.fdmgroup.SoloProject.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private UserRepository userRepository;

	// Creates an order from the user's cart and returns the created OrderDto
	@Transactional
	public OrderDto createOrder(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
		Cart cart = cartRepository.findByUser_UserId(userId)
				.orElseThrow(() -> new RuntimeException("Cart not found for user ID: " + userId));
		if (cart.getCartItems().isEmpty()) {
			throw new RuntimeException("Cannot create an order from an empty cart");
		}

		OrderEntity order = new OrderEntity();
		order.setUser(user);
		order.setOrderDate(new java.sql.Date(new Date().getTime()));
		order.setTotalAmount(cart.getTotalAmount());
		// Convert CartItems to OrderItems and set to order
		order.setOrderItems(convertCartItemsToOrderItems(cart.getCartItems(), order));
		OrderEntity savedOrder = orderRepository.save(order);

		// Optionally clear the user's cart after creating the order
		clearUserCart(cart);

		return convertToDto(savedOrder);
	}

	// Retrieves all orders and returns them as a list of OrderDto
	public List<OrderDto> findAllOrders() {
		return orderRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
	}

	// Finds a specific order by its ID and returns it as OrderDto
	public OrderDto findOrderById(Long orderId) {
		OrderEntity order = orderRepository.findById(orderId)
				.orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));
		return convertToDto(order);
	}

	// Helper method to convert OrderEntity to OrderDto
	private OrderDto convertToDto(OrderEntity order) {
		List<OrderItemDto> orderItemsDto = order.getOrderItems().stream().map(this::convertOrderItemToDto)
				.collect(Collectors.toList());
		return new OrderDto(order.getOrderId(), order.getUser().getUserId(), order.getTotalAmount(),
				order.getOrderDate(), orderItemsDto);
	}

	// Converts CartItems to OrderItems
	private List<OrderItem> convertCartItemsToOrderItems(List<CartItem> cartItems, OrderEntity order) {
		return cartItems.stream().map(cartItem -> {
			OrderItem orderItem = new OrderItem();
			orderItem.setOrder(order);
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setQuantity(cartItem.getQuantity());
			return orderItem;
		}).collect(Collectors.toList());
	}

	// Helper method to clear user's cart
	private void clearUserCart(Cart cart) {
		cart.getCartItems().clear();
		cartRepository.save(cart);
	}

	// Converts OrderItem to OrderItemDto
	private OrderItemDto convertOrderItemToDto(OrderItem orderItem) {
		return new OrderItemDto(orderItem.getOrderItemId(), orderItem.getOrder().getOrderId(),
				orderItem.getProduct().getProductId(), orderItem.getQuantity());
	}
}