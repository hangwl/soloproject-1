package com.fdmgroup.SoloProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.SoloProject.dto.UserDto;
import com.fdmgroup.SoloProject.dto.UserRegistrationDto;
import com.fdmgroup.SoloProject.model.Cart;
import com.fdmgroup.SoloProject.model.User;
import com.fdmgroup.SoloProject.repository.CartRepository;
import com.fdmgroup.SoloProject.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

	// Registers a new user and returns the UserDto of the registered user
	public UserDto registerUser(UserRegistrationDto registrationDto) {
		User newUser = new User();
		newUser.setUsername(registrationDto.getUsername());
		newUser.setPassword(registrationDto.getPassword());
		newUser.setEmail(registrationDto.getEmail());
		newUser.setAddress(registrationDto.getAddress());

		User savedUser = userRepository.save(newUser);
		
		Cart newCart = new Cart(savedUser);
		cartRepository.save(newCart);
		
		return convertToDto(savedUser);
	}

	// Authenticates a user based on username and password
	public UserDto authenticateUser(String username, String password) {
        return userRepository.findByUsername(username)
                .filter(user -> user.getPassword().equals(password))
                .map(this::convertToDto)
                .orElse(null);
    }

	// Finds a user by their ID and returns a UserDto
	public UserDto findUserById(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		return convertToDto(user);
	}

	// Updates the user details
	public void updateUser(UserDto userDto) {
		User user = userRepository.findById(userDto.getUserId())
				.orElseThrow(() -> new RuntimeException("User not found"));
		user.setEmail(userDto.getEmail());
		user.setAddress(userDto.getAddress());
		// Update other fields as necessary
		userRepository.save(user);
	}

	// Converts a User entity to UserDto
	private UserDto convertToDto(User user) {
		return new UserDto(user.getUserId(), user.getUsername(), user.getEmail(), user.getAddress());
	}
}