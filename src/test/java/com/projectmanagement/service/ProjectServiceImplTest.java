package com.projectmanagement.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.projectmanagement.entity.Project;
import com.projectmanagement.exceptions.ResourceNotFoundException;
import com.projectmanagement.repo.ProjectRepo;


@ExtendWith(MockitoExtension.class)
class ProjectServiceImplTest {
	@InjectMocks
	private ProjectServiceImpl projectServiceImpl;

	@Mock
	private ProjectRepo projectRepo;

	@Test
	public void testAllocateProject() throws Exception {
		Project project = new Project();
		project.setProjectId(1L);
		project.setProjectName("TestProject");
		when(projectRepo.save(project)).thenReturn(project);
		Project savedProject = projectServiceImpl.allocateProject(project);
		assertEquals("TestProject", savedProject.getProjectName());
	}
	@Test
	public void testDisAllocateProject() throws ResourceNotFoundException {
		Project project = new Project();
		project.setProjectId(1L);
		project.setProjectName("TestProject");
		when(projectRepo.findById(1L)).thenReturn(Optional.of(project));
		String response = projectServiceImpl.disAllocateProject(1L);
		assertEquals("Project disAllocated successfully", response);
	}
	@Test
	public void testDisAllocateProjectNotFoundException() {
		when(projectRepo.findById(1L)).thenReturn(Optional.empty());
		assertThrows(ResourceNotFoundException.class, () -> projectServiceImpl.disAllocateProject(1L));
	}
	@Test
	public void testModifyProject() throws ResourceNotFoundException {
		Project project = new Project();
		project.setProjectId(1L);
		project.setProjectName("TestProject");
		when(projectRepo.findById(1L)).thenReturn(Optional.of(project));
		when(projectRepo.save(project)).thenReturn(project);
		Project updatedProject = projectServiceImpl.modifyProject(project);
		assertEquals("TestProject", updatedProject.getProjectName());
	}
	@Test
	public void testModifyProjectNotFoundException() {
		Project project = new Project();
		project.setProjectId(1L);
		project.setProjectName("TestProject");
		when(projectRepo.findById(1L)).thenReturn(Optional.empty());
		assertThrows(ResourceNotFoundException.class, () -> projectServiceImpl.modifyProject(project));
	}
	@Test
	public void testGetProjectByProjectId() throws ResourceNotFoundException {
		Project project = new Project();
		project.setProjectId(1L);
		project.setProjectName("TestProject");
		when(projectRepo.findById(1L)).thenReturn(Optional.of(project));
		Project retrievedProject = projectServiceImpl.getProjectByProjectId(1L);
		assertEquals("TestProject", retrievedProject.getProjectName());
	}



}
	