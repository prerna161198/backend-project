package com.test.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.test.model.Advertisement;
import com.test.model.AdvertisementImages;

import com.test.service.AdvertisementImagesService;


@RestController
public class AdvertisementImagesController
{
	@Autowired
	private AdvertisementImagesService advertisementImagesService;
	
	@PostMapping("/advertisement/images")
	public ResponseEntity<?> addAdvImage(
			@RequestParam("file") MultipartFile image,
			@RequestParam("advertisement") Advertisement advertisement
			)
	{
		Map<String,String> response = new HashMap<>();
		
		try
		{
			AdvertisementImages advertisementImages=advertisementImagesService.addImage(image,advertisement);
			response.put("Status", "Success");
			response.put("Message","Advertisement Information Saved...");
			return new ResponseEntity<Map>(response,HttpStatus.CREATED);
		}
		catch(Exception e)
		{
			response.put("status", "failed");
			response.put("message", e.getMessage());
			return new ResponseEntity<Map>(response,HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/advertisement/images/{id}")
	public ResponseEntity<?> getAdvImageByAdvId(@PathVariable Long id)
	{
		Map<String,String> response = new HashMap<>();
		
		List<AdvertisementImages> advImages=advertisementImagesService.getAdvImagesByAdvertisementId(id);
		if(advImages.isEmpty())
		{
			response.put("status", "failed");
			response.put("message", "No record found");
			return new ResponseEntity<Map>(response,HttpStatus.BAD_REQUEST);
		}
		else
		{
			return new ResponseEntity<List>(advImages,HttpStatus.CREATED);
		}
	}
	
}