package com.test.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.model.Category;
import com.test.model.Message;
import com.test.service.CategoryService;

@RestController
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/Category")
	public ResponseEntity<?> addCategory(@Valid @RequestBody Category category)
	{
		
		Category createCategory= categoryService.addCategory(category);
		
		Map<String,String> response = new HashMap<>();
		response.put("Status", "Success");
		response.put("Message","Category Created...");
		return new ResponseEntity<Map>(response,HttpStatus.CREATED);
	}
	
	@GetMapping("/GetCategories")
	public List<Category> getAllCategories()
	{
		List<Category> categories=categoryService.getAllCategories();
		return categories;
	}

	@GetMapping("/GetCategory/{name}")
	public ResponseEntity<?> getCategory(@PathVariable String name)
	{
		
		Optional<Category> category=categoryService.getCategoryByName(name);
		
		if(category.isPresent())
		{
			return new ResponseEntity<Category>(category.get(),HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Category Name Not Found",HttpStatus.NOT_FOUND);
		}
	}
}
