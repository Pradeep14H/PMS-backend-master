package com.projectmanagement.service;

import java.util.List;

import com.projectmanagement.entity.Task;
import com.projectmanagement.exceptions.ResourceNotFoundException;

public interface TaskService {

	Task addTask(Task task) throws Exception;

	Task modifyTask(Task task) throws ResourceNotFoundException;

	Task getTaskById(long taskId) throws ResourceNotFoundException;

	String deleteTask(long taskId) throws ResourceNotFoundException;

	List<Task> getListOfTasks();

}
