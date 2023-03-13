package com.projectmanagement.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



@Entity

public class Task {

	@Id
	private long taskId;

	private String taskName;

	private String taskDescription;

	private String taskStatus;

	private LocalDate taskStartDate;

	private LocalDate endDate;

	private String feedBack;

	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employees;

	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public LocalDate getTaskStartDate() {
		return taskStartDate;
	}

	public void setTaskStartDate(LocalDate taskStartDate) {
		this.taskStartDate = taskStartDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getFeedBack() {
		return feedBack;
	}

	public void setFeedBack(String feedBack) {
		this.feedBack = feedBack;
	}

	public Employee getEmployees() {
		return employees;
	}

	public void setEmployees(Employee employees) {
		this.employees = employees;
	}

	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", taskName=" + taskName + ", taskDescription=" + taskDescription
				+ ", taskStatus=" + taskStatus + ", taskStartDate=" + taskStartDate + ", endDate=" + endDate
				+ ", feedBack=" + feedBack + ", employees=" + employees + "]";
	}

	
	
	
	
	

	
}
