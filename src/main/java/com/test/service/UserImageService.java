package com.test.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.test.model.User;
import com.test.model.UserImage;
import com.test.repository.UserImageRepository;

@Service
public class UserImageService {
	
	@Autowired
	private UserImageRepository userImageRepository;
	
	public void saveImageInfo(String imagePath) {
		UserImage image=new UserImage();
		image.setProfilePicture(imagePath);
		userImageRepository.save(image);
	}
	
	public Optional<UserImage> getProfilePicture(Long id)
	{
		return userImageRepository.findById(id);
	}

}
