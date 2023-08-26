package com.test.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.test.model.Advertisement;

import com.test.model.Category;
import com.test.model.User;

import com.test.service.AdvertisementService;
import com.test.service.CategoryService;
import com.test.service.UserService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins="http://localhost:4200")
public class AdvertisementController {
	@Autowired
	private AdvertisementService advertisementService;
	
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;
	 
	@PostMapping("/advertisement")
	public ResponseEntity<?> addAdvertisementInfo(@RequestBody Advertisement advertisement) 
	{
		Map<String,String> response = new HashMap<>();
		
		try
		{
			Advertisement adv = advertisementService.addDetails( advertisement);
			
			
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
	
	@GetMapping("/advertisement/{id}")
	public ResponseEntity<?>  getAdvertisement(@PathVariable Long id) throws Exception
	{
		
		Optional<Advertisement> advertisement=advertisementService.getAdvertisementById(id);
		Map<String,String> response = new HashMap<>();
		if(advertisement.isPresent())
		{
			
			return new ResponseEntity<Advertisement>(advertisement.get(),HttpStatus.OK);
		}
		else
		{
			response.put("status", "failed");
			response.put("message", "Id not found");
			return new ResponseEntity<Map>(response,HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/advertisement/user/{id}")
	public  ResponseEntity<?>  getAdvertisementByUSer(@PathVariable Long id) throws Exception
	{
		
		Map<String,String> response = new HashMap<>();
		
	        List<Advertisement> advertisement=advertisementService.getAdvertisementByUserId(id);
			
	        
			if(advertisement.isEmpty())
			{
				
				response.put("status", "failed");
				response.put("message", "No Advertisement found for this User");
				return new ResponseEntity<Map>(response,HttpStatus.BAD_REQUEST);
				
			}
			else
			{
				
				return new ResponseEntity<List>(advertisement,HttpStatus.OK);
			}
		
		
		
		
	}
	
	@GetMapping("/advertisement/category/{name}")
	public ResponseEntity<?>  getAdvertisementByCategory(@PathVariable String name) throws Exception
	{
		Map<String,String> response = new HashMap<>();
        Optional<Category> check=categoryService.getCategoryByName(name);
		
        
		if(check.isEmpty())
		{
			
			response.put("status", "failed");
			response.put("message", "No Category found");
			return new ResponseEntity<Map>(response,HttpStatus.BAD_REQUEST);
			
		}
		else
		{
			long id=check.get().getId();
			
			List<Advertisement> advertisement=advertisementService.getAdvertisementByCategoryId(id);
			if(advertisement.isEmpty())
			{
				response.put("status", "failed");
				response.put("message", "No Advertisement Found for this Category");
				return new ResponseEntity<Map>(response,HttpStatus.BAD_REQUEST);
			}
			else
			{
				return new ResponseEntity<List>(advertisement,HttpStatus.OK);
			}
		}
		
		
	}
	
	@GetMapping("/advertisements")
	public List<Advertisement> getAllAdvertisements()
	{
		List<Advertisement> advertisements= advertisementService.getAllAdvertisements();
		return advertisements;
	}
	
	@DeleteMapping("delete/advertisement/{id}")
	public ResponseEntity<?> deleteAdvertisement(@PathVariable Long id)
	{
		
		Optional<Advertisement> advertisement=advertisementService.getAdvertisementById(id);
		
		if(advertisement.isPresent())
		{
			advertisementService.deleteAdvById(id);
			return new ResponseEntity<String>("Deleted successfully",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Advertisement Id Not Found",HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/delete/advertisement/user/{id}")
	public ResponseEntity<?> deleteAdvertisementByUSer(@PathVariable Long id)
	{
		
        List<Advertisement> advertisement=advertisementService.getAdvertisementByUserId(id);
		
        
		if(advertisement.isEmpty())
		{
			return new ResponseEntity<String>("Enter Valid Id",HttpStatus.NOT_FOUND);
			
		}
		else
		{
			advertisementService.deleteAdvByUserId(id);
			return new ResponseEntity<String>("Deleted Successfully",HttpStatus.OK);
		}
		
		
	}
	
	@DeleteMapping("/delete/advertisement/category/{name}")
	public ResponseEntity<?> deleteAdvertisementByCategory(@PathVariable String name)
	{
		
        Optional<Category> check=categoryService.getCategoryByName(name);
		
        
		if(check.isEmpty())
		{
			
			return new ResponseEntity<String>("Advertisement Not Found",HttpStatus.NOT_FOUND);
			
		}
		else
		{
			long id=check.get().getId();
			
			List<Advertisement> advertisement=advertisementService.getAdvertisementByCategoryId(id);
			if(advertisement.isEmpty())
			{
				return new ResponseEntity<String>("There are no advertisements to delete",HttpStatus.NOT_FOUND);
			}
			else
			{
				advertisementService.deleteAdvByCategoeyId(id);
				return new ResponseEntity<String>("Deleted Successfully",HttpStatus.OK);
			}
		}
		
		
	}
	
	@PutMapping("/advertisement/{id}")
	public ResponseEntity<?> updateAdvertisement(
			@PathVariable Long id,
			
			@RequestParam("title") String title,
			@RequestParam("text") String text,
			@RequestParam("image") String image,
			@RequestParam("status") String status,
			@RequestParam("location") String location,
			@RequestParam("lastDate") Date lastDate,
			@RequestParam("price") double price
			
			) throws IOException
	{
		
		Optional<Advertisement> existingAdvertisement=advertisementService.getAdvertisementById(id);
		
		Map<String,String> response = new HashMap<>();
		if(existingAdvertisement.isPresent())
		{
			if(lastDate!=null)
			{
				existingAdvertisement.get().setLastDate(lastDate);
			}
			if(location!=null)
			{
				existingAdvertisement.get().setLocation(location);
			}
			if(price!=0)
			{
				existingAdvertisement.get().setPrice(price);
			}
			if(text!=null)
			{
				existingAdvertisement.get().setText(text);
			}
			if(status!=null)
			{
				existingAdvertisement.get().setStatus(status);
			}
			if(title!=null)
			{
				existingAdvertisement.get().setTitle(title);
			}
			if(image!=null)
			{
				existingAdvertisement.get().setImage(image);
			}
			
			advertisementService.updateAdvertisement(existingAdvertisement.get());
			response.put("Status", "Success");
			response.put("Message","Advertisement Information Updated");
			return new ResponseEntity<Map>(response,HttpStatus.CREATED);
		}
		else
		{
			response.put("Status", "Failed");
			response.put("Message","Advertisement Id Not Found...");
			return new ResponseEntity<Map>(response,HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/advertisement/location/{name}")
	public ResponseEntity<?> getAdvertisementByLocation(@PathVariable String name)
	{
		
        List<Advertisement> advertisement=advertisementService.getAdvertisementByLocation(name);
		
        
		if(advertisement.isEmpty())
		{
			
			return new ResponseEntity<String>("Advertisement Not Found For Selected Location",HttpStatus.NOT_FOUND);
			
		}
		else
		{
			
			return new ResponseEntity<List>(advertisement,HttpStatus.OK);
			
		}
		
		
	}
	
	
}   


