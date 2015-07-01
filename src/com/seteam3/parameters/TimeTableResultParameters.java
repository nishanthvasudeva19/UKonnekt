package com.seteam3.parameters;

public class TimeTableResultParameters {
	String courseID = "";
	String courseTopic = "";
	int status = 0;
	String date = "";
	String start_time = "";
	String end_time = "";

	public TimeTableResultParameters(String courseID, String courseTopic,
			int status, String date, String start_time, String end_time) {
		
		this.courseID = courseID;
		this.courseTopic = courseTopic;
		this.status = status;
		this.date = date;
		this.start_time = start_time;
		this.end_time = end_time;
		
	}

	public String getCourseID() {
		return courseID;
	}

	public String getCourseTopic() {
		return courseTopic;
	}

	public int isStatus() {
		return status;
	}

	public String getDate() {
		return date;
	}

	public String getStart_time() {
		return start_time;
	}

	public String getEnd_time() {
		return end_time;
	}
	
	
}
