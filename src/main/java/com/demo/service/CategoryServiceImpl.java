package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entity.Category;
import com.demo.entity.Product;
import com.demo.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public List<Category> getCategories() {
		
		return (List<Category>)categoryRepository.findAll();
	}

	@Override
	public Category getCategoryById(Long id) throws Exception {
		
		return categoryRepository.findById(id).orElseThrow(null);
	}

	

}
