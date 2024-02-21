package com.fdmgroup.SoloProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.SoloProject.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
