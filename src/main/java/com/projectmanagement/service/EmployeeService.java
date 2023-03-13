package com.projectmanagement.service;

import java.util.List;

import com.projectmanagement.entity.Admin;
import com.projectmanagement.entity.Employee;
import com.projectmanagement.exceptions.ResourceNotFoundException;

public interface EmployeeService {

	Employee addEmployee(Employee employee) throws Exception;

	Employee updateEmployee(Employee employee) throws ResourceNotFoundException;

	Employee deleteEmployee(Long employeeId) throws ResourceNotFoundException;

	List<Employee> getAllEmployees();
	
	Employee getEmployeeById(long employeeId) throws ResourceNotFoundException;
	
	String checkTaskStatus(long taskId) throws ResourceNotFoundException;
	
	Employee authenticateEmployee(String userName,String password) throws Exception;

}
