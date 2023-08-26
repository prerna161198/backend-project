package com.test.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.test.model.Advertisement;
import com.test.model.User;
import com.test.model.UserImage;
import com.test.service.FilesStorageService;
import com.test.service.UserImageService;
import com.test.service.UserService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins="http://localhost:4200")
public class UserImageController {
	
	@Autowired
	private UserImageService userImageService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FilesStorageService filesStorageService;
	
	
	
	/*@PostMapping("/userimage")
	public ResponseEntity<?> addUserImage(@RequestParam("image") MultipartFile image,@RequestParam("user") User user) throws IOException
	{
		Optional<UserImage> check=userImageService.getUserImage(user.getId());
		Map<String, String> response=new HashMap<>();
		if(check.isEmpty()) {
			
			if(image!=null)
			{
				this.filesStorageService.save(image);
			}
			try
			{
				
				
				userImageService.addUserImage(image.getOriginalFilename(), user);
				
				response.put("status", "success");
				response.put("message", "file uploaded successfully!!");
				return new ResponseEntity<Map>(response,HttpStatus.CREATED);
				
				
			}
			catch(Exception e)
			{
				response.put("status", "failed");
				response.put("message", e.getMessage());
				return new ResponseEntity<Map>(response,HttpStatus.BAD_REQUEST);
			}
		}
		else
		{
			response.put("status", "failed");
			response.put("message","Profile Picture for user this has been uploaded already");
			return new ResponseEntity<Map>(response,HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping(value="/files/{profilepicture}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<Resource> getFile(@PathVariable("profilepicture") String filename) {
      Resource file = filesStorageService.load(filename);
      return ResponseEntity.ok()
          .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }*/
	
	@PostMapping("/upload")
	public ResponseEntity<String> uploadImage(@RequestParam("imagePath") String imagePath){
		
		
		userImageService.saveImageInfo(imagePath);
		return ResponseEntity.ok("Image uploaded Successfully");
	}

	@GetMapping("/GetImage/{id}")
	public ResponseEntity<?> getImage(@PathVariable Long id)
	{
		Optional<UserImage> image=userImageService.getProfilePicture(id);
		return new ResponseEntity<UserImage>(image.get(),HttpStatus.OK);
	}
	
}
