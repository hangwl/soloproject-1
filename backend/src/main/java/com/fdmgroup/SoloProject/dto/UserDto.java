package com.fdmgroup.SoloProject.dto;

public class UserDto {

	private Long userId;
	private String username;
	private String email;
	private String address;
	
	public UserDto() {
		super();
	}
	
	public UserDto(Long userId, String username, String email, String address) {
		super();
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.address = address;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
