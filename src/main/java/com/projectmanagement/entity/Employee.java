package com.projectmanagement.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity

@PrimaryKeyJoinColumn(name = "employeeId")
public class Employee extends User {

	//@Id
	//private long employeeId;

	private String employeeName;

	private long mobileNumber;

	private String role="EMPLOYEE";

	private String employeeSalary;

//	@JsonBackReference
//	@OneToMany(cascade = CascadeType.ALL,mappedBy = "employees")
//	//@JoinColumn(name = "task_fk")
//	private List<Task> task = new ArrayList<>();
	
	private int ratings;

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmployeeSalary() {
		return employeeSalary;
	}

	public void setEmployeeSalary(String employeeSalary) {
		this.employeeSalary = employeeSalary;
	}

//	public List<Task> getTask() {
//		return task;
//	}
//
//	public void setTask(List<Task> task) {
//		this.task = task;
//	}

	public int getRatings() {
		return ratings;
	}

	public void setRatings(int ratings) {
		this.ratings = ratings;
	}

	@Override
	public String toString() {
		return "Employee [employeeName=" + employeeName + ", mobileNumber=" + mobileNumber + ", role=" + role
				+ ", employeeSalary=" + employeeSalary +  ", ratings=" + ratings + "]";
	}
	
	

	

}
