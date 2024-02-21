package com.fdmgroup.SoloProject.dto;

public class ProductDto {

	private Long productId;
	private String name;
	private double price;
	private String description;
	
	public ProductDto() {
		super();
	}
	
	public ProductDto(Long productId, String name, double price, String description) {
		super();
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.description = description;
	}
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
