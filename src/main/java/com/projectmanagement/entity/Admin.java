package com.projectmanagement.entity;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;


@Entity

@PrimaryKeyJoinColumn(name = "admin_Id")
public class Admin extends User {

	 private String role="ADMIN";

	public String getRole() {
		return role;
	}

	@Override
	public String toString() {
		return "Admin [role=" + role + "]";
	}

	public void setRole(String role) {
		this.role = role;
	}
	 

	

	
}
