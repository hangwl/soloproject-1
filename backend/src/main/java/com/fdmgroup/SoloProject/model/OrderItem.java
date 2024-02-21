package com.fdmgroup.SoloProject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long orderItemId;

	@ManyToOne
	@JoinColumn(name = "orderId")
	private OrderEntity order;

	@ManyToOne
	@JoinColumn(name = "productId")
	private Product product;

	private int quantity;

	public OrderItem() {
		super();
	}

	public OrderItem(OrderEntity order, Product product, int quantity) {
		super();
		this.order = order;
		this.product = product;
		this.quantity = quantity;
	}

	public long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(long orderItemId) {
		this.orderItemId = orderItemId;
	}

	public OrderEntity getOrder() {
		return order;
	}

	public void setOrder(OrderEntity order) {
		this.order = order;
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
