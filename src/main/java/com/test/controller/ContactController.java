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

import com.test.model.Contact;
import com.test.model.Message;
import com.test.service.ContactService;

@RestController
public class ContactController {
	
	@Autowired
	private ContactService contactService;
	
	@PostMapping("/ContactUs")
	public ResponseEntity<?> contactUs(@Valid @RequestBody Contact contact)
	{
		
		Contact contactUs= contactService.contactUs(contact);
		
		Map<String,String> response = new HashMap<>();
		response.put("Status", "Success");
		response.put("Message","Message Sent...");
		return new ResponseEntity<Map>(response,HttpStatus.CREATED);
	}

}
