package com.projectmanagement.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projectmanagement.entity.Task;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {
//	@Query(value = "SELECT * FROM rating WHERE playerid = :playerId", nativeQuery = true)
//    List<Task> ratingsByPlayerId(@Param("playerId") Long playerId);
	
	@Query(value = "SELECT * FROM task WHERE employee_id = :empId", nativeQuery = true)
	List<Task> getTasksByEmpId(@Param("empId") Long empId);

}
