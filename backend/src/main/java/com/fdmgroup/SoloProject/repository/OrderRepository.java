package com.fdmgroup.SoloProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.SoloProject.model.OrderEntity;
import com.fdmgroup.SoloProject.model.User;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
	List<OrderEntity> findByUser(User user);
}