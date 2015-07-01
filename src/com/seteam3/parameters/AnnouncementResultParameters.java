package com.seteam3.parameters;

public class AnnouncementResultParameters {
	private String dueDate;
	private String title;
	private String description;
	
	public AnnouncementResultParameters(String title, String dueDate, String description) {
		
		this.title = title;
		this.dueDate = dueDate;
		this.description = description;
	}
	
	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
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



}
