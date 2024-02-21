package com.fdmgroup.SoloProject.dto;

public class OrderItemDto {

	private Long orderItemId;
	private Long orderId;
    private Long productId;
    private int quantity;
    
	public OrderItemDto() {
		super();
	}
	
	public OrderItemDto(Long orderItemId, Long orderId, Long productId, int quantity) {
		super();
		this.orderItemId = orderItemId;
		this.orderId = orderId;
		this.productId = productId;
		this.quantity = quantity;
	}

	public Long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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

}
