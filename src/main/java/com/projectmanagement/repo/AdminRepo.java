package com.projectmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projectmanagement.entity.Admin;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Long> {
	@Query(value = "select a from Admin a where a.userName=?1")
	public Admin authenticateAdmin(String userName);

}
