package com.fdmgroup.SoloProject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.SoloProject.dto.ProductDto;
import com.fdmgroup.SoloProject.model.Product;
import com.fdmgroup.SoloProject.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	// Retrieves all products and returns them as a list of ProductDto
	public List<ProductDto> findAllProducts() {
		return productRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
	}

	// Finds a product by ID and returns it as ProductDto
	public ProductDto findProductById(Long productId) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Product not found"));
		return convertToDto(product);
	}

	// Saves or updates a product based on ProductDto
	public ProductDto saveProduct(ProductDto productDto) {
		Product product = new Product();
		if (productDto.getProductId() != null) {
			product = productRepository.findById(productDto.getProductId()).orElse(product);
		}
		product.setName(productDto.getName());
		product.setPrice(productDto.getPrice());
		product.setDescription(productDto.getDescription());
		Product savedProduct = productRepository.save(product);
		return convertToDto(savedProduct);
	}

	// Deletes a product by ID
	public void deleteProduct(Long productId) {
		productRepository.deleteById(productId);
	}

	// Converts a Product entity to ProductDto
	private ProductDto convertToDto(Product product) {
		return new ProductDto(product.getProductId(), product.getName(), product.getPrice(), product.getDescription());
	}
}
