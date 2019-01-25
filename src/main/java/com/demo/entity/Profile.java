package com.demo.entity;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.owasp.html.PolicyFactory;

@Entity
@Table(name = "profile")
public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "profile_id")
	private Long id;

	@OneToOne
	@JoinColumn(name = "userid")
	private User user;
	
	@OneToMany(mappedBy = "profile")	
	private List<Comment> comments = new ArrayList<>();	

	@Column(name = "about", length = 5000)
	@Size(max = 5000, message = "message must be < 5000")
	private String about;
	
	@Column(name = "photo_directory", length = 10)
	private String photoDirectory;

	@Column(name = "photo_name", length = 10)
	private String photoName;

	@Column(name = "photo_extension", length = 5)
	private String photoExtension;

	public String getPhotoDirectory() {
		return photoDirectory;
	}
	
	@OneToMany(mappedBy = "authorProfile") 
	 private List<Product> products = new ArrayList<>();

	public void setPhotoDirectory(String photoDirectory) {
		this.photoDirectory = photoDirectory;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public String getPhotoExtension() {
		return photoExtension;
	}

	public void setPhotoExtension(String photoExtension) {
		this.photoExtension = photoExtension;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public void copyFrom(Profile profile) {
		if (profile.about != null) {
			this.about = profile.about;
		}
	}
	
	@OneToMany(mappedBy = "profile")	
	private List<Message> messages = new ArrayList<>();	

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public void mergeFrom(Profile webProfile, PolicyFactory htmlPolicy) {
		if (webProfile.about != null) {
			this.about = htmlPolicy.sanitize(webProfile.about);
		}
	}
	
	public void setPhotoDetails(FileInfo info) {
		photoDirectory = info.getSubDirectory();
		photoExtension = info.getExtension();
		photoName = info.getBasename();
	}
	
	public Path getPhoto(String baseDirectory) {
		if(photoName == null) {
			return null;
		}
		
		return Paths.get(baseDirectory, photoDirectory, photoName + "." +  photoExtension);

		}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
}
