package com.test.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.model.Message;
import com.test.model.User;
import com.test.repository.MessageRepository;

@Service
public class MessageServiceImplementation implements MessageService{

	@Autowired
	private MessageRepository messageRepository;
	
	@Override
	public Message sendMessage(Message message) {
		
		return messageRepository.save(message);
	}

	@Override
	public List<Message> getAllMessages() {
		
		return messageRepository.findAll();
	}

	@Override
	public void deleteMessageById(Long id)
	{
		messageRepository.deleteById(id);
	}

	@Override
	public void deleteAllMessages() {
		
		messageRepository.deleteAll();
	}

	@Override
	public Optional<Message> getMessageById(Long id) {
		
		return messageRepository.findById(id);
	}

}
