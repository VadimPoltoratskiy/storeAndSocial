package com.demo.service;
import com.demo.entity.Product;
import java.util.List;

public interface ProductService {
	
	public List<Product> getProducts();
	
	public Product getProductById(Long id);
	
	public List<Product> getProductsByCategoryId(int id);
}

