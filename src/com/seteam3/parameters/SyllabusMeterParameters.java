package com.seteam3.parameters;

public class SyllabusMeterParameters {
	
	String courseID = "" ;
	
	public SyllabusMeterParameters(String courseID)
	{
		this.courseID = courseID;
	}
	
	public String getCourseID()
	{
		return this.courseID;
	}
}
