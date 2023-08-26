package com.test.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.model.Category;
import com.test.model.Message;
import com.test.repository.CategoryRepository;

@Service
public class CategoryServiceImplementation implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Category addCategory(Category category) {
		
		return categoryRepository.save(category);
	}

	@Override
	public Optional<Category> getCategoryByName(String name) {
		
		return categoryRepository.findCategoryByName(name);
	}

	@Override
	public List<Category> getAllCategories() {
		
		return categoryRepository.findAll();
	}

}
