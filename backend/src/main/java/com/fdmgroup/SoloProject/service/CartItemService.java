package com.fdmgroup.SoloProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.SoloProject.dto.CartItemDto;
import com.fdmgroup.SoloProject.model.CartItem;
import com.fdmgroup.SoloProject.repository.CartItemRepository;

import jakarta.transaction.Transactional;

@Service
public class CartItemService {

	@Autowired
	private CartItemRepository cartItemRepository;

	// Finds a specific cart item by its ID
	public CartItemDto findCartItemById(Long cartItemId) {
		CartItem cartItem = cartItemRepository.findById(cartItemId)
				.orElseThrow(() -> new RuntimeException("CartItem not found"));
		return convertToDto(cartItem);
	}

	// Updates a cart item quantity
	@Transactional
	public CartItemDto updateCartItemQuantity(Long cartItemId, int quantity) {
		CartItem cartItem = cartItemRepository.findById(cartItemId)
				.orElseThrow(() -> new RuntimeException("CartItem not found"));
		cartItem.setQuantity(quantity);
		CartItem updatedCartItem = cartItemRepository.save(cartItem);
		return convertToDto(updatedCartItem);
	}

	// Removes a cart item
	@Transactional
	public void removeCartItem(Long cartItemId) {
		cartItemRepository.deleteById(cartItemId);
	}

	// Helper method to convert a CartItem entity to a DTO
	private CartItemDto convertToDto(CartItem cartItem) {
		CartItemDto dto = new CartItemDto();
		dto.setCartItemId(cartItem.getCartItemId());
		dto.setProductId(cartItem.getProduct().getProductId());
		dto.setQuantity(cartItem.getQuantity());
		// Add other necessary fields and conversions
		return dto;
	}
}