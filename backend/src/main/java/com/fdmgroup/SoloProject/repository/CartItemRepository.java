package com.fdmgroup.SoloProject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.SoloProject.model.Cart;
import com.fdmgroup.SoloProject.model.CartItem;
import com.fdmgroup.SoloProject.model.Product;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

	Optional<CartItem> findByCartAndProduct(Cart cart, Product product);
	
	void deleteAllByCart(Cart cart);

}
