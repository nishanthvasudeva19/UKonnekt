package com.seteam3.parameters;

public class SyllabusResultParameters {
	
	int totalClasses = 0 ;
	int completedClasses = 0 ;
	
	public SyllabusResultParameters(int totalClasses, int completedClasses){
		
		this.totalClasses = totalClasses;
		this.completedClasses = completedClasses;
		
	}

	public int getTotalClasses() {
		return totalClasses;
	}

	public int getCompletedClasses() {
		return completedClasses;
	}
	
	

}
