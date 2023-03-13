package com.projectmanagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.projectmanagement.entity.Employee;
import com.projectmanagement.entity.Task;
import com.projectmanagement.entity.Team;
import com.projectmanagement.exceptions.ResourceNotFoundException;
import com.projectmanagement.repo.EmployeeRepo;
import com.projectmanagement.repo.ProjectRepo;
import com.projectmanagement.repo.TaskRepo;
import com.projectmanagement.repo.TeamRepo;
import com.projectmanagement.service.TaskService;

@RestController
@CrossOrigin(origins="*")
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@Autowired
	private TaskRepo taskRepo;
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private TeamRepo teamRepo;
	
	@Autowired
	private ProjectRepo projectRepo;

	@GetMapping({ "/admin/get-task-by-id/{id}", "/team/get-task-by-id/{id}" })
	public ResponseEntity<?> getTaskById(@PathVariable long id) throws ResourceNotFoundException {
		return ResponseEntity.ok(taskService.getTaskById(id));
	}

	@PostMapping("/admin/add-task" )
	public ResponseEntity<?> addTask(@RequestBody Task task) throws Exception {
		return new ResponseEntity<>(taskService.addTask(task), HttpStatus.CREATED);
	}

	@PutMapping({ "/admin/update-task" })
	public ResponseEntity<?> updateTask(@RequestBody Task task) throws ResourceNotFoundException {
		return ResponseEntity.ok(taskService.modifyTask(task));
	}

	@GetMapping("/admin/get-all-list-of-tasks")
	public ResponseEntity<?> getListOfTasks() {
		return ResponseEntity.ok(taskService.getListOfTasks());
	}

	@DeleteMapping("/admin/delete-task/{taskId}")
	public ResponseEntity<?> deleteTask(@PathVariable long taskId) throws ResourceNotFoundException {
		return ResponseEntity.ok(taskService.deleteTask(taskId));
	}
	
	
	//Newly Added 
	@GetMapping("/employee/tasks/{empId}")
	public ResponseEntity<?> getTasksByEmpId(@PathVariable long empId) throws ResourceNotFoundException {
		employeeRepo.findById(empId)
		.orElseThrow(() -> new ResourceNotFoundException("Employee Details Not found : "));
		return ResponseEntity.ok(taskRepo.getTasksByEmpId(empId));
	}
	
	@GetMapping("/admin/gettasksbyproject/{pid}")
	public ResponseEntity<?> getAllTasksByPId(@PathVariable long pid) throws ResourceNotFoundException {
		projectRepo.findById(pid)
		.orElseThrow(() -> new ResourceNotFoundException("Project Not Found with ID : " + pid));
		List<Team> teams = teamRepo.getTeamsByPId(pid);
		List<Employee> employees = new ArrayList<>();

	    for (Team team : teams) {
	        for (Employee employee : team.getTeamMembers()) {
	            if (!employees.contains(employee)) {
	                employees.add(employee);
	            }
	        }
	    }
	    
	    List<Task> tasks = new ArrayList<>();
	    for (Employee employee :employees) {
	    	for(Task task: taskRepo.getTasksByEmpId(employee.getUserId())) {
	    		tasks.add(task);
	    	}
	    }

	    return ResponseEntity.ok(tasks);
	}
	
	@GetMapping("/admin/gettasksbyteam/{tid}")
	public ResponseEntity<?> getAllTasksByTeamId(@PathVariable long tid) throws ResourceNotFoundException {
		Team team = teamRepo.findById(tid)
		.orElseThrow(() -> new ResourceNotFoundException("Team doesn't exists with id : " + tid));
		List<Employee> employees = new ArrayList<>();
        for (Employee employee : team.getTeamMembers()) {
            if (!employees.contains(employee)) {
                employees.add(employee);
            }
        }
	    List<Task> tasks = new ArrayList<>();
	    for (Employee employee :employees) {
	    	for(Task task: taskRepo.getTasksByEmpId(employee.getUserId())) {
	    		tasks.add(task);
	    	}
	    }
	    return ResponseEntity.ok(tasks);
	}
	

}
