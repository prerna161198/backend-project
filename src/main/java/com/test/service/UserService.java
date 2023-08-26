package com.test.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.test.model.User;


public interface UserService {
	
	public User addUser(User user);
	
	public Optional<User> getUserById(Long id);
	
	public Optional<User> getUserProfile(String email);
	
	public List<User> getAllUser();
	public void deleteUserById(Long id);
	public User updateUser(User user);
	public Optional<User> getUserByEmail(String email);

	
	public Optional<User> getUserByPassword(String password);
	

	

}
