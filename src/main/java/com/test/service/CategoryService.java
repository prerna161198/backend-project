package com.test.service;

import java.util.List;
import java.util.Optional;

import com.test.model.Category;
import com.test.model.Message;

public interface CategoryService {
	
	public Category addCategory(Category category);
	
	public Optional<Category> getCategoryByName(String name);
	
	public List<Category> getAllCategories();

}
