package com.fdmgroup.SoloProject.dto;

import java.util.List;

public class CartDto {

	private Long cartId;
	private double totalAmount;
	private List<CartItemDto> cartItems;

	public CartDto() {
		super();
	}

	public CartDto(Long cartId, double totalAmount, List<CartItemDto> cartItems) {
		super();
		this.cartId = cartId;
		this.totalAmount = totalAmount;
		this.cartItems = cartItems;
	}

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<CartItemDto> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItemDto> cartItems) {
		this.cartItems = cartItems;
	}

}
