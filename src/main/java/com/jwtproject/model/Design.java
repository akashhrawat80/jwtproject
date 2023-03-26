package com.jwtproject.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="design")
public class Design {
	@Id
	String id;
	String title;
	String description;
	String creator;
	String price;
	String creation_date;
	String last_update_date;
	List<String> tags;
	public Design() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Design(String title, String description, String creator, String price, String creation_date,
			String last_update_date, List<String> tags) {
		super();
		this.title = title;
		this.description = description;
		this.creator = creator;
		this.price = price;
		this.creation_date = creation_date;
		this.last_update_date = last_update_date;
		this.tags = tags;
	}
	public Design(String title) {
		super();
		this.title = title;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(String creation_date) {
		this.creation_date = creation_date;
	}
	public String getLast_update_date() {
		return last_update_date;
	}
	public void setLast_update_date(String last_update_date) {
		this.last_update_date = last_update_date;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	
}
