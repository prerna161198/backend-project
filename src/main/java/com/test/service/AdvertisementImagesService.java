package com.test.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.test.model.Advertisement;
import com.test.model.AdvertisementImages;
import com.test.repository.AdvertisementImagesRepository;


@Service
public class AdvertisementImagesService {
	
	@Autowired
	private AdvertisementImagesRepository AdvertisementImagesRepository;
	
	public AdvertisementImages addImage(MultipartFile image,Advertisement advertisement) throws IOException
	{
		AdvertisementImages advertisementImages=new AdvertisementImages();
		advertisementImages.setImage(image.getBytes());
		advertisementImages.setAdvertisement(advertisement);
		return AdvertisementImagesRepository.save(advertisementImages);
	}
	
	public List<AdvertisementImages> getAdvImagesByAdvertisementId(Long id)
	{
		return AdvertisementImagesRepository.findAdvImgByAdvId(id);
	}

}
