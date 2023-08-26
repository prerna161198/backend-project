package com.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.model.Admin;
import com.test.repository.AdminRepository;

@Service
public class AdminServiceImplementation implements AdminService{

	@Autowired
	private AdminRepository adminRepository;

	@Override
	public Admin addAdmin(Admin role) {
		
		return adminRepository.save(role);
	}

}
