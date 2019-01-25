package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.entity.Message;
import com.demo.repository.MessageRepository;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageRepository messageRepository;	
	
	@Override
	@Transactional
	public void add(Message message) {		 
		messageRepository.save(message);
	}
	
	@Override
	@Transactional
	public List<Message> getMessages() {		 
		return (List<Message>) messageRepository.findAll();
	}

	@Override
	@Transactional
	public Message getMessageById(Long id) {		 
		return messageRepository.findById(id).orElseThrow(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		messageRepository.deleteMessage(id);
	}
	


	@Override
	@Transactional
	public List<Message> getMessagesByProfileId(Long id) {		
		return messageRepository.findMessagesByProfileId(id);
	}
	
	@Override
	@Transactional
	public List<Message> getSentMessagesByProfileId(Long id) {		
		return messageRepository.findSentMessagesByProfileId(id);
	}

	
	@Override
	@Transactional
	public void open(Long id) {
		messageRepository.openMessage(id);			
	}

	@Override
	@Transactional
	public int getUnreadCount(Long id) {
		return messageRepository.getUnreadCount(id);
	}

}


