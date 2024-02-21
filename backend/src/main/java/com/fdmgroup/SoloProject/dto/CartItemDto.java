package com.fdmgroup.SoloProject.dto;

public class CartItemDto {
	
	private Long cartItemId;
    private Long productId;
    private int quantity;
    private String productName;
    private double productPrice;

	public CartItemDto() {
		super();
	}

	public CartItemDto(Long cartItemId, Long productId, int quantity, String productName, double productPrice) {
		super();
		this.cartItemId = cartItemId;
		this.productId = productId;
		this.quantity = quantity;
		this.productName = productName;
		this.productPrice = productPrice;
	}

	public Long getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(Long cartItemId) {
		this.cartItemId = cartItemId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

}
