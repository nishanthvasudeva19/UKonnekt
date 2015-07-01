package com.seteam3.tasks;

public class LoginResult {
	
	private int errorId;
	private int UniversityID;
	private String firstName;
	private String LastName;

	
	public LoginResult(int errorId, int UniversityID, String firstName, String lastName ) {
		this.errorId = errorId;
		this.UniversityID = UniversityID;
		this.firstName = firstName;
		this.LastName = lastName;
	}
	
	public int GetErrorId()
	{
		return errorId;
	}

	public int getUniversityID() {
		return UniversityID;
	}


	public String getFirstName() {
		return firstName;
	}

	
	public String getLastName() {
		return LastName;
	}


	


}
