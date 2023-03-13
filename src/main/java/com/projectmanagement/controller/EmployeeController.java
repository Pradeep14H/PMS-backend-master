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
import org.springframework.web.bind.annotation.RestController;

import com.projectmanagement.entity.Employee;
import com.projectmanagement.entity.Task;
import com.projectmanagement.entity.Team;
import com.projectmanagement.exceptions.ResourceNotFoundException;
import com.projectmanagement.repo.EmployeeRepo;
import com.projectmanagement.repo.ProjectRepo;
import com.projectmanagement.repo.TeamRepo;
import com.projectmanagement.service.EmployeeService;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private TeamRepo teamRepo;
	
	@Autowired
	private ProjectRepo projectRepo;
	
	@Autowired
	private EmployeeRepo employeeRepo;

	@GetMapping( {"/admin/get-employee-by-id/{employeeId}", "/employee/get-employee-by-id/{employeeId}"} )
	public ResponseEntity<?> getEmployeeById(@PathVariable long employeeId) throws ResourceNotFoundException {
		return ResponseEntity.ok(employeeService.getEmployeeById(employeeId));
	}

	@PostMapping({ "/admin/addemployee" } )
	public ResponseEntity<?> registerEmployee(@RequestBody Employee employee) throws Exception {
		return new ResponseEntity<>(employeeService.addEmployee(employee), HttpStatus.CREATED);
	}

	@DeleteMapping("/admin/delete-employee/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable long id) throws ResourceNotFoundException {
		return ResponseEntity.ok(employeeService.deleteEmployee(id));
	}

	@GetMapping("/admin/get-all-employee")
	public ResponseEntity<?> getAllListOfEmployees() {
		return ResponseEntity.ok(employeeService.getAllEmployees());
	}

	@PutMapping({ "/admin/update-employee", "/employee/update-employee" })
	public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) throws ResourceNotFoundException {
		return ResponseEntity.ok(employeeService.updateEmployee(employee));
	}

	@GetMapping({ "/admin/check-task-status by task-id/{taskId}", "/employee/check-task-status by task-id/{taskId}" })
	public String checkTaskStatus(@PathVariable long taskId) throws ResourceNotFoundException {
		return employeeService.checkTaskStatus(taskId);
	}

	@PostMapping("/employee/authenticate/{userName}/{password}")
	public ResponseEntity<?> authenticate(@PathVariable String userName, @PathVariable String password)
			throws Exception {
		return ResponseEntity.ok(employeeService.authenticateEmployee(userName, password));
	}

	
	@GetMapping("/admin/getempsbyproject/{pid}")
	public ResponseEntity<?> getAllTeamsByPId(@PathVariable long pid) throws ResourceNotFoundException {
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

	    return ResponseEntity.ok(employees);
		
	}
	@GetMapping("/admin/getempsbyteam/{tid}")
	public ResponseEntity<?> getAllEmpsByTeamId(@PathVariable long tid) throws ResourceNotFoundException {
		Team team = teamRepo.findById(tid)
		.orElseThrow(() -> new ResourceNotFoundException("Team doesn't exists with id : " + tid));
		List<Employee> employees = team.getTeamMembers();
        
	    return ResponseEntity.ok(employees);
	}
	
//	@GetMapping("/teams/getteamsbyempid/{employeeId}")
//	public ResponseEntity<List<Team>> getTeamsByEmployeeId(@PathVariable Long employeeId) throws ResourceNotFoundException {
//	    Employee employee = employeeRepo.findById(employeeId)
//	            .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID : " + employeeId));
//
//	    List<Team> teams = teamRepo.getTeamsByEmployeeId(employeeId);
//
//	    if (teams.isEmpty()) {
//	        return ResponseEntity.noContent().build();
//	    }
//
//	    return ResponseEntity.ok(teams);
//	}

}
