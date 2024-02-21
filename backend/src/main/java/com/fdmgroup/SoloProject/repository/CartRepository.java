package com.fdmgroup.SoloProject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.SoloProject.model.Cart;
import com.fdmgroup.SoloProject.model.User;

public interface CartRepository extends JpaRepository<Cart, Long> {

	Optional<Cart> findByUser(User user);

	Optional<Cart> findByUser_UserId(Long userId);

}
