package com.fdmgroup.SoloProject.controller;

import java.net.URI;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fdmgroup.SoloProject.dto.UserDto;
import com.fdmgroup.SoloProject.dto.UserLoginDto;
import com.fdmgroup.SoloProject.dto.UserRegistrationDto;
import com.fdmgroup.SoloProject.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody UserRegistrationDto registrationDto) {
		
		if (userService.existsByUsername(registrationDto.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(Collections.singletonMap("message", "Username is already taken!"));
        }
		
		UserDto userDto = userService.registerUser(registrationDto);

		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{id}")
				.buildAndExpand(userDto.getUserId()).toUri();

		return ResponseEntity.created(location).body(userDto);
	}

	@PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@RequestBody UserLoginDto loginDto) {
        UserDto userDto = userService.authenticateUser(loginDto.getUsername(), loginDto.getPassword());

        if (userDto != null) {
            return ResponseEntity.ok(userDto);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}