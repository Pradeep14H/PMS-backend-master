package com.projectmanagement.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projectmanagement.entity.Team;

@Repository
public interface TeamRepo extends JpaRepository<Team, Long> {
	
	@Query(value = "SELECT * FROM team WHERE project_id = :pid", nativeQuery = true)
	List<Team> getTeamsByPId(@Param("pid") Long pid);
	
	
//	@Query(value = "SELECT t FROM Team t JOIN t.teamMembers e WHERE e.id = :employeeId", nativeQuery = true)
//	List<Team> getTeamsByEmployeeId(@Param("employeeId") Long employeeId);
	
	
}
