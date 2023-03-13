package com.projectmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectmanagement.entity.Task;
import com.projectmanagement.exceptions.ResourceNotFoundException;
import com.projectmanagement.repo.TaskRepo;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepo taskRepo;

	@Override
	public Task addTask(Task task) throws Exception {
		return taskRepo.save(task);
	}

	@Override
	public Task modifyTask(Task task) throws ResourceNotFoundException {
		Task t = taskRepo.findById(task.getTaskId())
				.orElseThrow(() -> new ResourceNotFoundException("Task Doesn't exists in the db "));
		taskRepo.delete(t);
		return taskRepo.save(task);
	}

	@Override
	public Task getTaskById(long taskId) throws ResourceNotFoundException {

		return taskRepo.findById(taskId)
				.orElseThrow(() -> new ResourceNotFoundException("Task Doesn't exists with id : " + taskId));
	}

	@Override
	public List<Task> getListOfTasks() {

		return taskRepo.findAll();
	}

	@Override
	public String deleteTask(long taskId) throws ResourceNotFoundException {
		Task t = taskRepo.findById(taskId)
				.orElseThrow(() -> new ResourceNotFoundException("Task Doesn't exists with id : " + taskId));
		taskRepo.delete(t);
		return t.getTaskName()+" Task Deleted Successfully....";
	}

}
