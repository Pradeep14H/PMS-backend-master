package com.projectmanagement.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.projectmanagement.entity.Employee;
import com.projectmanagement.entity.Task;
import com.projectmanagement.exceptions.ResourceNotFoundException;
import com.projectmanagement.repo.EmployeeRepo;
import com.projectmanagement.repo.TaskRepo;


class EmployeeServiceImplTest {
	@Mock
	private EmployeeRepo employeeRepo;

	@Mock
	private TaskRepo taskRepo;

	@InjectMocks
	private EmployeeServiceImpl employeeService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}
	@Test
	public void testAddEmployee() throws Exception {
		Employee employee = new Employee();
		employee.setUserId(1L);
		employee.setUserName("john.doe");
		employee.setUserPassword("password");
		when(employeeRepo.findById(1L)).thenReturn(Optional.empty());
		when(employeeRepo.save(employee)).thenReturn(employee);

		Employee addedEmployee = employeeService.addEmployee(employee);

		assertNotNull(addedEmployee);
		assertEquals(1L, addedEmployee.getUserId());
		assertEquals("john.doe", addedEmployee.getUserName());
		assertEquals("password", addedEmployee.getUserPassword());
	}
	@Test
	public void testUpdateEmployee() throws ResourceNotFoundException {
		Employee employee = new Employee();
		employee.setUserId(1L);
		employee.setUserName("john.doe");
		employee.setUserPassword("password");
		when(employeeRepo.findById(1L)).thenReturn(Optional.of(employee));
		when(employeeRepo.save(employee)).thenReturn(employee);

		Employee updatedEmployee = employeeService.updateEmployee(employee);

		assertNotNull(updatedEmployee);
		assertEquals(1L, updatedEmployee.getUserId());
		assertEquals("john.doe", updatedEmployee.getUserName());
		assertEquals("password", updatedEmployee.getUserPassword());
	}
	@Test
	public void testDeleteEmployee() throws ResourceNotFoundException {
		Employee employee = new Employee();
		employee.setUserId(1L);
		employee.setUserName("john.doe");
		employee.setUserPassword("password");
		when(employeeRepo.findById(1L)).thenReturn(Optional.of(employee));

		Employee deletedEmployee = employeeService.deleteEmployee(1L);

		assertNotNull(deletedEmployee);
		assertEquals(1L, deletedEmployee.getUserId());
		assertEquals("john.doe", deletedEmployee.getUserName());
		assertEquals("password", deletedEmployee.getUserPassword());
	}

}	