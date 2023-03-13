package com.projectmanagement.service;

import java.util.List;

import com.projectmanagement.entity.Project;
import com.projectmanagement.exceptions.ResourceNotFoundException;

public interface ProjectService {

	Project allocateProject(Project project) throws Exception;

	String disAllocateProject(long projectId) throws ResourceNotFoundException;

	Project modifyProject(Project project) throws ResourceNotFoundException;

	List<Project> getAllProject() throws ResourceNotFoundException;
	
	Project getProjectByProjectId(long projectId) throws ResourceNotFoundException;

}
