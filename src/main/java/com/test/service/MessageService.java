package com.test.service;

import java.util.List;
import java.util.Optional;

import com.test.model.Message;


public interface MessageService {

	public Message sendMessage(Message message);
	public List<Message> getAllMessages();
	public void deleteMessageById(Long id);
	public void deleteAllMessages();
	public Optional<Message> getMessageById(Long id);
	
}
