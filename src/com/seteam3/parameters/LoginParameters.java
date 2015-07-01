package com.seteam3.parameters;

public class LoginParameters {
	private String name;
	private String password;
	private String stuOrProf;
	
	public LoginParameters (String name, String password, String stuOrProf) {
		this.name=name;
		this.password=password;
		this.stuOrProf=stuOrProf;
	}

	public String getName()
	{
		return name;
	}

	public String getPassword()
	{
		return password;
	}
	
	public String getstuOrProf()
	{
		return stuOrProf;
	}

}
