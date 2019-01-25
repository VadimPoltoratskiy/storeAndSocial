package com.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long id;	
	
	@Column(name="title", length=100)	
	private String title;	
	
	@Column(name="product_text", length=500)	
	private String productText;	
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name = "author_id")
	private Profile authorProfile;
	
	/*@Column(name = "category_id")
	private Long category;за ненаодбностью, в виду связи двух таблиц, была нужна до того как распределили товары по категориям */	
	
	@Column(name = "price")
	private Long price;	
	
	@Column(name="photo_directory", length=10)	
	private String photoDirectory;	
	
	@Column(name="photo_extension", length=5)	
	private String photoExtension;	
	
	@Column(name="photo_name", length=10)	
	private String photoName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProductText() {
		return productText;
	}

	public void setProductText(String productText) {
		this.productText = productText;
	}

	public Profile getAuthorProfile() {
		return authorProfile;
	}

	public void setAuthorProfile(Profile authorProfile) {
		this.authorProfile = authorProfile;
	}

	
	/*public Long getCategory() {
		return category;
	}

	public void setCategory(Long category) {
		this.category = category;
	}*/

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getPhotoDirectory() {
		return photoDirectory;
	}

	public void setPhotoDirectory(String photoDirectory) {
		this.photoDirectory = photoDirectory;
	}

	public String getPhotoExtension() {
		return photoExtension;
	}

	public void setPhotoExtension(String photoExtension) {
		this.photoExtension = photoExtension;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

}
