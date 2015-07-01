package com.seteam3.parameters;

public class TimeTableParameters {
	
	String todayDate = "";
	String resultsFor =  "";
	
	public TimeTableParameters(String date, String resultsFor){
		
		this.todayDate = date;
		this.resultsFor = resultsFor;
	}

	public String gettodayDate()
	{
		return todayDate;
	}
	
	public String getResultsFor()
	{
		return resultsFor;
	}
}
