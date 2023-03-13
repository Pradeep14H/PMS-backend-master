package com.projectmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectmanagement.entity.Project;
import com.projectmanagement.exceptions.ResourceNotFoundException;
import com.projectmanagement.repo.ProjectRepo;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepo projectRepo;

	@Override
	public Project allocateProject(Project project) throws Exception {
//		if (projectRepo.findById(project.getProjectId()) != null) {
//			throw new Exception("Project already exists with id : " + project.getProjectId());
//		}
		return projectRepo.save(project);
	}

	@Override
	public String disAllocateProject(long projectId) throws ResourceNotFoundException {
		Project p = projectRepo.findById(projectId)
				.orElseThrow(() -> new ResourceNotFoundException("Project Not Found with ID : " + projectId));
		projectRepo.delete(p);
		return "Project disAllocated successfully";
	}

	@Override
	public Project modifyProject(Project project) throws ResourceNotFoundException {
		Project p = projectRepo.findById(project.getProjectId()).orElseThrow(
				() -> new ResourceNotFoundException("Project Not Found with ID : " + project.getProjectId()));
		projectRepo.delete(p);
		return projectRepo.save(project);
	}

	@Override
	public List<Project> getAllProject() throws ResourceNotFoundException {
		if (projectRepo.findAll().isEmpty()) {
			throw new ResourceNotFoundException("Projects are Not Found In DB ");
		}
		return projectRepo.findAll();
	}

	@Override
	public Project getProjectByProjectId(long projectId) throws ResourceNotFoundException {

		return projectRepo.findById(projectId).orElseThrow(
				() -> new ResourceNotFoundException("Project Details are Not found with id : " + projectId));
	}

}
