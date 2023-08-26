package com.test.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.test.model.User;
import com.test.repository.UserRepository;
import org.springframework.util.StringUtils;


@Service
public class UserServiceImplementation implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User addUser(User user ){
		
		
		
		
		return userRepository.save(user);
	}

	@Override
	public Optional<User> getUserById(Long id) {
		
		return userRepository.findById(id);
	}

	@Override
	public List<User> getAllUser() {
		
		return userRepository.findAll();
	}

	@Override
	public void deleteUserById(Long id) {
		
		userRepository.deleteById(id);
	}

	@Override
	public User updateUser(User user) {
		
		return userRepository.save(user);
	}

	@Override
	public Optional<User> getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findUserByEmail(email);
	}

	@Override
	public Optional<User> getUserProfile(String email) {
		// TODO Auto-generated method stub
		return userRepository.findUserProfile(email);
	}

	@Override
	public Optional<User> getUserByPassword(String password) {
		// TODO Auto-generated method stub
		return userRepository.findUserByPassword(password);
	}

	
	
}

