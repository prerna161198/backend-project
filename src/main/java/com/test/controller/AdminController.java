package com.test.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.model.Admin;
import com.test.model.User;
import com.test.service.AdminService;

@RestController
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@PostMapping("/admin")
	public ResponseEntity<?> addAdmin(@Valid @RequestBody Admin admin)
	{
		
		Admin saveAdmin = adminService.addAdmin(admin);
		
		Map<String,String> response = new HashMap<>();
		response.put("Status", "Success");
		response.put("Message"," Information Saved...");
		return new ResponseEntity<Map>(response,HttpStatus.CREATED);
	}
}
