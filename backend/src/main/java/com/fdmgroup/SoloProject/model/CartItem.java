package com.fdmgroup.SoloProject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long cartItemId;

	@ManyToOne
	@JoinColumn(name = "cartId")
	private Cart cart;

	@ManyToOne
	@JoinColumn(name = "productId")
	private Product product;

	private int quantity;

	public CartItem() {
		super();
	}

	public CartItem(Cart cart, Product product, int quantity) {
		super();
		this.cart = cart;
		this.product = product;
		this.quantity = quantity;
	}

	public long getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(long cartItemId) {
		this.cartItemId = cartItemId;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
