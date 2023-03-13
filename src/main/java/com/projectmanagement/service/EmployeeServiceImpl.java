package com.projectmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectmanagement.entity.Employee;
import com.projectmanagement.entity.Task;
import com.projectmanagement.exceptions.ResourceNotFoundException;
import com.projectmanagement.repo.EmployeeRepo;
import com.projectmanagement.repo.TaskRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;
	@Autowired
	private TaskRepo taskRepo;

	@Override
	public Employee addEmployee(Employee employee) throws Exception {
		if (employeeRepo.findById(employee.getUserId()).isPresent()) {
			throw new Exception("Employee Already Exists with id : " + employee.getUserId());
		}

		return employeeRepo.save(employee);
	}

	@Override
	public Employee updateEmployee(Employee employee) throws ResourceNotFoundException {
		Employee emp = employeeRepo.findById(employee.getUserId()).orElseThrow(
				() -> new ResourceNotFoundException("Employee Not Found with id :" + employee.getUserId()));
		employeeRepo.delete(emp);
		return employeeRepo.save(employee);
	}

	@Override
	public Employee deleteEmployee(Long employeeId) throws ResourceNotFoundException {
		Employee emp = employeeRepo.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee Not Found with id :" + employeeId));
		employeeRepo.delete(emp);
		return emp;
	}

	@Override
	public List<Employee> getAllEmployees() {

		return employeeRepo.findAll();
	}

	@Override
	public Employee getEmployeeById(long employeeId) throws ResourceNotFoundException {

		return employeeRepo.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee Details Not found : "));
	}

	@Override
	public String checkTaskStatus(long taskId) throws ResourceNotFoundException {
		Task t = taskRepo.findById(taskId)
				.orElseThrow(() -> new ResourceNotFoundException("Task doesn't exists with id : " + taskId));

		return t.getTaskStatus();
	}

	@Override
	public Employee authenticateEmployee(String userName, String password) throws Exception {
		Employee e = employeeRepo.authenticateEmployee(userName);
		;
//		try {
//			e = employeeRepo.authenticateEmployee(userName);
//		} catch (Exception ex) {
//			throw new Exception("Invalid credentails");
//		}
		System.out.println(e);
		if (e!=null && e.getUserName().equals(userName) && e.getUserPassword().equals(password)) {
			return e;
		} else {
			throw new Exception("Invalid credentails");
		}
	}

}
