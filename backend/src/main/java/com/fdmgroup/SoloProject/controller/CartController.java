package com.fdmgroup.SoloProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.SoloProject.dto.CartDto;
import com.fdmgroup.SoloProject.dto.CartItemDto;
import com.fdmgroup.SoloProject.dto.OrderDto;
import com.fdmgroup.SoloProject.service.CartService;
import com.fdmgroup.SoloProject.service.OrderService;

@RestController
@RequestMapping("/api/carts")
public class CartController {

	@Autowired
	private CartService cartService;

	@Autowired
	private OrderService orderService;

	@GetMapping("/user/{userId}")
	public ResponseEntity<CartDto> getCart(@PathVariable Long userId) {
		CartDto cartDto = cartService.getCartByUserId(userId);
		return ResponseEntity.ok(cartDto);
	}

	@PostMapping("/user/{userId}/add")
	public ResponseEntity<Void> addItemToCart(@PathVariable Long userId, @RequestBody CartItemDto cartItemDto) {
		cartService.addToCart(userId, cartItemDto);
		return ResponseEntity.ok().build();
	}
	
    @DeleteMapping("/user/{userId}/item/{cartItemId}")
    public ResponseEntity<?> removeItemFromCart(@PathVariable Long userId, @PathVariable Long cartItemId) {
        cartService.removeItemFromCart(userId, cartItemId);
        return ResponseEntity.ok().build();
    }

	@PostMapping("/user/{userId}/clear")
	public ResponseEntity<Void> clearCart(@PathVariable Long userId) {
		cartService.clearCart(userId);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/user/{userId}/checkout")
	public ResponseEntity<OrderDto> checkoutCart(@PathVariable Long userId) {
		OrderDto orderDto = orderService.createOrder(userId);
		cartService.clearCart(userId);
		return ResponseEntity.ok(orderDto);
	}

}