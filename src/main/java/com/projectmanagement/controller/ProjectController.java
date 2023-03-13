package com.projectmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projectmanagement.entity.Project;
import com.projectmanagement.exceptions.ResourceNotFoundException;
import com.projectmanagement.service.ProjectService;

@RestController
@CrossOrigin(origins="*")
public class ProjectController {
	@Autowired
	private ProjectService projectService;

	@GetMapping("/admin/get-project-by-id/{projectId}")
	public ResponseEntity<?> getProjectById(@PathVariable long projectId) throws ResourceNotFoundException {
		return ResponseEntity.ok(projectService.getProjectByProjectId(projectId));
	}

	@PostMapping("/admin/add-project")
	public ResponseEntity<?> addProject(@RequestBody Project project) throws Exception {
		return new ResponseEntity<>(projectService.allocateProject(project), HttpStatus.CREATED);
	}

	@DeleteMapping("/admin/delete-project/{projectId}")
	public ResponseEntity<?> deleteProject(@PathVariable long projectId) throws ResourceNotFoundException {
		return ResponseEntity.ok(projectService.disAllocateProject(projectId));
	}

	@GetMapping("/admin/get-all-projects")
	public ResponseEntity<?> getall() throws ResourceNotFoundException {
		return ResponseEntity.ok(projectService.getAllProject());
	}

	@PutMapping("/admin/update-project")
	public ResponseEntity<?> updateProject(@RequestBody Project project) throws ResourceNotFoundException {
		return ResponseEntity.ok(projectService.modifyProject(project));
	}

}
