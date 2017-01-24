package com.gk.study.java.springdata.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table (name="users")
public class User  {
	@Id
	private int userId;
	private String firsName;
	private String lastName;
	
	public User(int userid, String firstName){
		setUserId(userid);
		setFirsName(firstName);		
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirsName() {
		return firsName;
	}

	public void setFirsName(String firsName) {
		this.firsName = firsName;
	}
	
}
