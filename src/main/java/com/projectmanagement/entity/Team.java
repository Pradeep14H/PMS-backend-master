package com.projectmanagement.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;



@Entity

public class Team {
	
	@Id
	private Long teamId;
	
	private String teamName;
	
	private String teamLeadName;
	
	@OneToMany
	@JoinColumn(name = "employee_fk")
	private List<Employee> teamMembers=new ArrayList<>();
	
	@OneToOne
	@JoinColumn(name="project_id")
	private Project project;

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamLeadName() {
		return teamLeadName;
	}

	public void setTeamLeadName(String teamLeadName) {
		this.teamLeadName = teamLeadName;
	}

	public List<Employee> getTeamMembers() {
		return teamMembers;
	}

	public void setTeamMembers(List<Employee> teamMembers) {
		this.teamMembers = teamMembers;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	

	
	

}
