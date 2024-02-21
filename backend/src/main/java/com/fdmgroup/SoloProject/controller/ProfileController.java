package com.fdmgroup.SoloProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.SoloProject.dto.UserDto;
import com.fdmgroup.SoloProject.service.UserService;

@RestController
@RequestMapping("/api/users")
public class ProfileController {

	@Autowired
	private UserService userService;

	@GetMapping("/profile/{userId}")
	public ResponseEntity<UserDto> viewProfile(@PathVariable Long userId) {
		UserDto userDto = userService.findUserById(userId);
		return ResponseEntity.ok(userDto);
	}

	@PostMapping("/profile/update")
	public ResponseEntity<UserDto> updateProfile(@RequestBody UserDto userDto) {
		userService.updateUser(userDto);
		return ResponseEntity.ok(userService.findUserById(userDto.getUserId()));
	}

}