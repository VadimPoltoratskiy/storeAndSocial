package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.entity.Product;
import com.demo.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Override
	@Transactional
	public List<Product> getProducts() {		 
		return (List<Product>) productRepository.findAll();
	}

	@Override
	@Transactional
	public Product getProductById(Long id) {	 
		return productRepository.findById(id).orElseThrow(null);
	}

	@Override
	@Transactional
	public List<Product> getProductsByCategoryId(int id) {
		
		return (List<Product>)productRepository.getProductsByCategoryId(id);
	}

	
	
}
