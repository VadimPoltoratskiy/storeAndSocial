package com.demo.service;

import java.util.List;
import com.demo.entity.Message;

public interface MessageService {
	
	public void add(Message message);

	public List<Message> getMessages();
	
	public List<Message> getMessagesByProfileId(Long id);
	
	public List<Message> getSentMessagesByProfileId(Long id);
	
	public Message getMessageById(Long id);
	
	public void delete(Long id);
		
	public void open(Long id);
	
	public int getUnreadCount(Long id);
}


