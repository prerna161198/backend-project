package com.test.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.model.Message;
import com.test.model.User;
import com.test.service.MessageService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins="http://localhost:4200")
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	
	@PostMapping("/SendMessage")
	public ResponseEntity<?> addMEssageInfo(@Valid @RequestBody Message message)
	{
		
		Message sendMessage= messageService.sendMessage(message);
		
		Map<String,String> response = new HashMap<>();
		response.put("Status", "Success");
		response.put("Message","Message Sent...");
		return new ResponseEntity<Map>(response,HttpStatus.CREATED);
	}
	
	@GetMapping("/GetMessage/{id}")
	public ResponseEntity<?> getMessage(@PathVariable Long id)
	{
		
		Optional<Message> message=messageService.getMessageById(id);
		
		if(message.isPresent())
		{
			return new ResponseEntity<Message>(message.get(),HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Book Id Not Found",HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/GetMessages")
	public List<Message> getAllMessages()
	{
		List<Message> messages=messageService.getAllMessages();
		return messages;
	}

	@DeleteMapping("/delete/message/{id}")
	public ResponseEntity<?> deleteMessage(@PathVariable Long id)
	{
		
		Optional<Message> message=messageService.getMessageById(id);
		
		Map<String,String> response = new HashMap<>();
		if(message.isPresent())
		{
			messageService.deleteMessageById(id);
			response.put("Status", "Success");
			response.put("Message","Message Deleted...");
			return new ResponseEntity<Map>(response,HttpStatus.OK);
		}
		else
		{
			response.put("Status", "Failed");
			response.put("Message"," Id Not Found...");
			return new ResponseEntity<Map>(response,HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/DeleteMessages")
	public ResponseEntity<?> deleteAllMessages()
	{
		messageService.deleteAllMessages();
		Map<String,String> response = new HashMap<>();
		response.put("Status", "Success");
		response.put("Message","All Messages Deleted...");
		return new ResponseEntity<Map>(response,HttpStatus.OK);
	}
}
