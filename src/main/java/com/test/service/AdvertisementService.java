package com.test.service;


import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.test.model.Advertisement;
import com.test.model.Category;
import com.test.model.User;


public interface AdvertisementService {
	
	public Advertisement addDetails(Advertisement advertisement) ;
	
	public Optional<Advertisement> getAdvertisementById(Long id);
	public List<Advertisement> getAdvertisementByUserId(Long id);
	public List<Advertisement> getAdvertisementByCategoryId(Long id);
	
	public List<Advertisement> getAdvertisementByLocation(String name);
	
	public List<Advertisement> getAllAdvertisements();
	
	public void deleteAdvById(Long id);
	public void deleteAdvByUserId(Long id);
	public void deleteAdvByCategoeyId(Long id);
	
	public Advertisement updateAdvertisement(Advertisement advertisement);

}
