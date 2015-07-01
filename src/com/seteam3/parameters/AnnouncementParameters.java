package com.seteam3.parameters;

public class AnnouncementParameters {
	private String courseId;
	private String annOrAssign;
	
	public AnnouncementParameters(String courseId, String annOrAssign) {
		
		this.courseId = courseId;
		this.annOrAssign = annOrAssign;
		}
	
	public String getCourseId() {
		return courseId;
	}

	public String getAnnOrAssign() {
		return annOrAssign;
	}

}
