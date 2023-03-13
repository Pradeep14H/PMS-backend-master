package com.projectmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projectmanagement.entity.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
	@Query(value = "select e from Employee e where e.userName=?1")
	Employee authenticateEmployee(String userName);

	

}
