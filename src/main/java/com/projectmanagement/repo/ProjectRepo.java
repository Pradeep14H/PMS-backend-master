package com.projectmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectmanagement.entity.Project;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Long> {

}
