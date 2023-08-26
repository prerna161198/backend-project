package com.test.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.test.model.Contact;
import com.test.repository.ContactRepository;

@Service
public class ContactServiceImplementation implements ContactService{

	@Autowired
	private ContactRepository contactRepository;
	
	@Override
	public Contact contactUs(Contact contact) {
		
		return contactRepository.save(contact);
	}

}
