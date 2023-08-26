package com.test.service;



import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.test.model.Advertisement;
import com.test.model.Category;
import com.test.model.User;
import com.test.repository.AdvertisementRepository;

@Service
public class AdvertisementServiceImplementation implements AdvertisementService {

	@Autowired
	private AdvertisementRepository advertisementRepository;
	
	@Override
	public Advertisement addDetails( Advertisement advertisement){
		
		
		
		return advertisementRepository.save(advertisement);
	}

	@Override
	public Optional<Advertisement> getAdvertisementById(Long id) {
		
		return advertisementRepository.findById(id);
	}

	@Override
	public List<Advertisement> getAdvertisementByUserId(Long id) {
		
		return advertisementRepository.findAdvertisementByUserId(id);
	}

	@Override
	public List<Advertisement> getAdvertisementByCategoryId(Long id) {
		
		return advertisementRepository.findAdvertisementByCategoryId(id);
	}

	@Override
	public List<Advertisement> getAllAdvertisements() {
		
		return advertisementRepository.findAll();
	}

	@Override
	public void deleteAdvById(Long id) {
		
		advertisementRepository.deleteById(id);
	}

	@Override
	public void deleteAdvByUserId(Long id) {
		
		advertisementRepository.deleteAdvertisementByUserId(id);
	}

	@Override
	public void deleteAdvByCategoeyId(Long id) {
		
		advertisementRepository.deleteAdvertisementByCategoryId(id);
	}

	@Override
	public Advertisement updateAdvertisement(Advertisement advertisement) {
		
		return advertisementRepository.save(advertisement);
	}

	@Override
	public List<Advertisement> getAdvertisementByLocation(String name) {
		
		return advertisementRepository.findAdvertisementByLocation(name);
	}

	

}
