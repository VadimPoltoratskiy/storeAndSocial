package com.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="message")
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "message_id")
	private Long id;
	
		
	@ManyToOne
	@JoinColumn(name = "to_id")
	private Profile profile;	
	
	@ManyToOne
	@JoinColumn(name = "from_id")
	private Profile fromProfile;
	
	@Column(name="message_text", length=1000)	
	private String messageText;
	
	@Column(name = "opened")
	private int opened;
	
	@Column(name = "recipient_deleted")
	private int recipientDeleted;
	
	@Column(name = "sender_deleted")
	private int senderDeleted;

	public Message() {
	
	}

	
	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}




	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public Profile getFromProfile() {
		return fromProfile;
	}


	public void setFromProfile(Profile fromProfile) {
		this.fromProfile = fromProfile;
	}


	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public int getOpened() {
		return opened;
	}

	public void setOpened(int opened) {
		this.opened = opened;
	}

	public int getRecipientDeleted() {
		return recipientDeleted;
	}

	public void setRecipientDeleted(int recipientDeleted) {
		this.recipientDeleted = recipientDeleted;
	}

	public int getSenderDeleted() {
		return senderDeleted;
	}

	public void setSenderDeleted(int senderDeleted) {
		this.senderDeleted = senderDeleted;
	}
	
}
