package com.qa.gorest.pojo;

/*
 * POJO class for User
 */
public class User {

	private String name;
	private String status;
	private String gender;
	private String email;
	
	//constructor
	public User(String name, String status, String gender, String email) {
		
		this.name = name;
		this.status = status;
		this.gender = gender;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
