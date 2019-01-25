package com.demo.service;

import java.util.List;

import com.demo.entity.Category;
import com.demo.entity.Product;


public interface CategoryService {
	
	public List<Category> getCategories();
	
	public Category getCategoryById(Long id) throws Exception;

	
	
	
	

}
