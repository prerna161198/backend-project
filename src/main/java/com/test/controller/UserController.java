package com.test.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.test.model.User;
import com.test.model.UserImage;
import com.test.repository.UserRepository;
import com.test.service.FilesStorageService;
import com.test.service.UserImageService;
import com.test.service.UserService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins="http://localhost:4200")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FilesStorageService filesStorageService;
	
	
	
	@PostMapping("/user")
	public ResponseEntity<?> addUserInfo(@RequestBody User user)
	{
		
		
		Map<String,String> response = new HashMap<>();
		try
		{
			User saveUser = userService.addUser(user);
			
			
			response.put("Status", "Success");
			response.put("Message","User Information Saved...");
			return new ResponseEntity<Map>(response,HttpStatus.CREATED);
		}
		catch(Exception e)
		{
			response.put("status", "failed");
			response.put("message", e.getMessage());
			return new ResponseEntity<Map>(response,HttpStatus.BAD_REQUEST);
		}
	}
	
	   
	/*@GetMapping("/user/{id}")
	public ResponseEntity<?>  getUser(@PathVariable Long id) throws Exception
	{
		
		Optional<User> user=userService.getUserById(id);
		Map<String,String> response = new HashMap<>();
		if(user.isPresent())
		{
			
		
			return new ResponseEntity<User>(user.get(),HttpStatus.OK);
		}
		else
		{
			response.put("status", "failed");
			response.put("message", "Id not found");
			return new ResponseEntity<Map>(response,HttpStatus.BAD_REQUEST);
		}
	}*/
	
	@GetMapping("/user/{email}")
	public ResponseEntity<?>  getProfile(@PathVariable String email) throws Exception
	{
		
		Optional<User> user=userService.getUserProfile(email);
		Map<String,String> response = new HashMap<>();
		if(user.isPresent())
		{
			
		
			return new ResponseEntity<User>(user.get(),HttpStatus.OK);
		}
		else
		{
			response.put("status", "failed");
			response.put("message", "User not found");
			return new ResponseEntity<Map>(response,HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping("/users")
	public List<User> getAllUsers()
	{
		List<User> users=userService.getAllUser();
		return users;
	}

	@DeleteMapping("/delete/user/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id)
	{
		
		Optional<User> user=userService.getUserById(id);
		
		Map<String,String> response = new HashMap<>();
		if(user.isPresent())
		{
			userService.deleteUserById(id);
			response.put("Status", "Success");
			response.put("Message","User Information Deleted...");
			return new ResponseEntity<Map>(response,HttpStatus.OK);
		}
		else
		{
			response.put("Status", "Failed");
			response.put("Message","User Id Not Found...");
			return new ResponseEntity<Map>(response,HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/user/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Long id,
			@RequestParam("name") String name,
			@RequestParam("email") String email,
			@RequestParam("gender") String gender,
			@RequestParam("profilePicture") String image,
			@RequestParam("password") String password,
			@RequestParam("mobile") long mobile,
			@RequestParam("address") String address,
			@RequestParam("city") String city,
			@RequestParam("country") String country,
			@RequestParam("pincode") long pincode,
			@RequestParam("state") String state
			) throws IOException
	{
		
		Optional<User> existingUser=userService.getUserById(id);
		
		Map<String,String> response = new HashMap<>();
		if(existingUser.isPresent())
		{
			if(address!=null)
			{
				existingUser.get().setAddress(address);
			}
			if(city!=null)
			{
				existingUser.get().setCity(city);
			}
			if(country!=null)
			{
				existingUser.get().setCountry(country);
			}
			if(email!=null)
			{
				existingUser.get().setEmail(email);
			}
			if(gender!=null)
			{
				existingUser.get().setGender(gender);
			}
			if(mobile!=0)
			{
				existingUser.get().setMobile(mobile);
			}
			if(name!=null)
			{
				existingUser.get().setName(name);
			}
			if(password!=null)
			{
				existingUser.get().setPassword(password);
			}
			if(pincode!=0)
			{
				existingUser.get().setPincode(pincode);
			}
			
			if(state!=null)
			{
				existingUser.get().setState(state);
			}
			if(image!=null)
			{
				existingUser.get().setProfilePicture(image);
			}
			userService.updateUser(existingUser.get());
			response.put("Status", "Success");
			response.put("Message","User Information Updated");
			return new ResponseEntity<Map>(response,HttpStatus.OK);
		}
		else
		{
			response.put("Status", "Failed");
			response.put("Message","User Id Not Found...");
			return new ResponseEntity<Map>(response,HttpStatus.NOT_FOUND);
		}
	}
	
	

    
	
	
	@PostMapping("user/login")
	public ResponseEntity<?> userLogin(@RequestParam("email") String email, @RequestParam("password") String password)
	{
		Optional<User> user = userService.getUserByEmail(email);
		Map<String,String> response = new HashMap<>();
		
		if(user.isPresent())
		{
			if(password.equals(user.get().getPassword()))
			{
				response.put("Status", "Success");
				response.put("Message","Successfully loggedin");
				return new ResponseEntity<Map>(response,HttpStatus.OK);
			}
			else
			{
				response.put("Status", "Failed");
				response.put("Message","Incorrect password");
				return new ResponseEntity<Map>(response,HttpStatus.NOT_FOUND);
			}
			
		}
		else
		{
			response.put("Status", "Failed");
			response.put("Message","No user found with this email");
			return new ResponseEntity<Map>(response,HttpStatus.NOT_FOUND);
		}
	}
	
	

	
}
