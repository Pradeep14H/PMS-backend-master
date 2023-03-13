package com.projectmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.projectmanagement.entity.Admin;
import com.projectmanagement.exceptions.ResourceNotFoundException;
import com.projectmanagement.repo.AdminRepo;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminRepo adminRepo;

	@Override
	public Admin addAdmin(Admin admin) {

		return adminRepo.save(admin);
	}

	@Override
	public Admin updateAdmin(Admin admin) throws ResourceNotFoundException {
		Admin a = adminRepo.findById(admin.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("Admin Details not present "));
		adminRepo.delete(a);
		return adminRepo.save(admin);
	}

	@Override
	public Admin authenticateAdmin(String userName, String password) throws Exception {
		Admin a = null;
		try {
			a = adminRepo.authenticateAdmin(userName);
		} catch (Exception e) {
			throw new Exception("Invalid Credentails");
		}
		if (a != null) {
			if (a.getUserName().equals(userName) && a.getUserPassword().equals(password)) {
				return a;
			}
		} else {
			throw new Exception("Invalid Credentails");
		}
		return null;
	}

}
