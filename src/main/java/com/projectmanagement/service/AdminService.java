package com.projectmanagement.service;

import com.projectmanagement.entity.Admin;
import com.projectmanagement.exceptions.ResourceNotFoundException;

public interface AdminService {

	Admin addAdmin(Admin admin);

	Admin updateAdmin(Admin admin) throws ResourceNotFoundException;
	
	Admin authenticateAdmin(String userName,String password) throws Exception;

}
