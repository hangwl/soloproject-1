package com.fdmgroup.SoloProject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.SoloProject.dto.CartDto;
import com.fdmgroup.SoloProject.dto.CartItemDto;
import com.fdmgroup.SoloProject.model.Cart;
import com.fdmgroup.SoloProject.model.CartItem;
import com.fdmgroup.SoloProject.model.Product;
import com.fdmgroup.SoloProject.repository.CartItemRepository;
import com.fdmgroup.SoloProject.repository.CartRepository;
import com.fdmgroup.SoloProject.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private ProductRepository productRepository;

	@Transactional
	public void addToCart(Long userId, CartItemDto cartItemDto) {
		Cart cart = cartRepository.findByUser_UserId(userId)
				.orElseThrow(() -> new RuntimeException("Cart not found for user ID: " + userId));

		Product product = productRepository.findById(cartItemDto.getProductId())
				.orElseThrow(() -> new RuntimeException("Product not found with ID: " + cartItemDto.getProductId()));

		CartItem cartItem = cartItemRepository.findByCartAndProduct(cart, product)
				.orElse(new CartItem(cart, product, 0));
		cartItem.setQuantity(cartItem.getQuantity() + cartItemDto.getQuantity());
		cartItemRepository.save(cartItem);
		updateCartTotal(cart);
	}

	public void removeItemFromCart(Long userId, Long cartItemId) {
	    Cart cart = cartRepository.findByUser_UserId(userId)
	            .orElseThrow(() -> new RuntimeException("Cart not found for user ID: " + userId));

	    CartItem cartItem = cartItemRepository.findById(cartItemId)
	            .orElseThrow(() -> new RuntimeException("Cart item not found"));

	    if (cartItem.getCart().getCartId() != cart.getCartId()) {
	        throw new RuntimeException("Cart item does not belong to the user's cart");
	    }

	    cartItemRepository.delete(cartItem);
	    updateCartTotal(cart);
	}
	
	private void updateCartTotal(Cart cart) {
		double totalAmount = cart.getCartItems().stream()
				.mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity()).sum();

		cart.setTotalAmount(totalAmount);
		cartRepository.save(cart);
	}

	@Transactional
	public void clearCart(Long userId) {
		Cart cart = cartRepository.findByUser_UserId(userId)
				.orElseThrow(() -> new RuntimeException("Cart not found for user ID: " + userId));
		
	    cart.getCartItems().clear();
	    cart.setTotalAmount(0);
	    cartRepository.save(cart);
	    cartItemRepository.deleteAllByCart(cart);
	}

	public CartDto getCartByUserId(Long userId) {
		Cart cart = cartRepository.findByUser_UserId(userId)
				.orElseThrow(() -> new RuntimeException("Cart not found for user ID: " + userId));

		return convertToDto(cart);
	}

	private CartDto convertToDto(Cart cart) {
		CartDto cartDto = new CartDto();
		cartDto.setCartId(cart.getCartId());
		cartDto.setTotalAmount(cart.getTotalAmount());

		List<CartItemDto> cartItemDtos = cart.getCartItems().stream().map(this::convertCartItemToDto)
				.collect(Collectors.toList());

		cartDto.setCartItems(cartItemDtos);
		return cartDto;
	}

	private CartItemDto convertCartItemToDto(CartItem cartItem) {
		CartItemDto cartItemDto = new CartItemDto();
		cartItemDto.setCartItemId(cartItem.getCartItemId());
		cartItemDto.setProductId(cartItem.getProduct().getProductId());
		cartItemDto.setQuantity(cartItem.getQuantity());
		cartItemDto.setProductName(cartItem.getProduct().getName());
		cartItemDto.setProductPrice(cartItem.getProduct().getPrice());
		return cartItemDto;
	}

}