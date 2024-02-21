package com.fdmgroup.SoloProject.dto;

import java.util.Date;
import java.util.List;

public class OrderDto {
	
	private Long orderId;
	private Long userId;
    private double totalAmount;
    private Date orderDate;
    private List<OrderItemDto> orderItems;
    
	public OrderDto() {
		super();
	}
	
	public OrderDto(Long orderId, Long userId, double totalAmount, Date orderDate, List<OrderItemDto> orderItems) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.totalAmount = totalAmount;
		this.orderDate = orderDate;
		this.orderItems = orderItems;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public List<OrderItemDto> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemDto> orderItems) {
		this.orderItems = orderItems;
	}

}
