package com.fdmgroup.SoloProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.SoloProject.dto.CartItemDto;
import com.fdmgroup.SoloProject.service.CartItemService;

@RestController
@RequestMapping("/api/cartItems")
public class CartItemController {

	@Autowired
	private CartItemService cartItemService;

	@GetMapping("/{cartItemId}")
	public ResponseEntity<CartItemDto> getCartItem(@PathVariable Long cartItemId) {
		CartItemDto cartItemDto = cartItemService.findCartItemById(cartItemId);
		return ResponseEntity.ok(cartItemDto);
	}

	@PutMapping("/{cartItemId}")
	public ResponseEntity<CartItemDto> updateCartItemQuantity(@PathVariable Long cartItemId,
			@RequestParam int quantity) {
		CartItemDto updatedCartItemDto = cartItemService.updateCartItemQuantity(cartItemId, quantity);
		return ResponseEntity.ok(updatedCartItemDto);
	}

	@DeleteMapping("/{cartItemId}")
	public ResponseEntity<Void> removeCartItem(@PathVariable Long cartItemId) {
		cartItemService.removeCartItem(cartItemId);
		return ResponseEntity.noContent().build();
	}

}