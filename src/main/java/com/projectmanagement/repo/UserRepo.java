package com.projectmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectmanagement.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>
{

}
