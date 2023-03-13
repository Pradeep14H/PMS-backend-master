 package com.projectmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectmanagement.entity.Admin;
import com.projectmanagement.exceptions.ResourceNotFoundException;
import com.projectmanagement.service.AdminService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@PostMapping("/register-admin")
	public ResponseEntity<?> registerAdmin(@RequestBody Admin admin) {
		return new ResponseEntity<>(adminService.addAdmin(admin), HttpStatus.CREATED);
	}

	@PostMapping( path="authenticate-admin/{userName}/{password}", consumes = {MediaType.ALL_VALUE})
	public ResponseEntity<?> authenticateAdmin(@PathVariable String userName, @PathVariable String password)
			throws Exception {
		return ResponseEntity.ok(adminService.authenticateAdmin(userName, password));
	}

	@PutMapping("/update-admin-details")
	public ResponseEntity<?> updateAdmin(@RequestBody Admin admin) throws ResourceNotFoundException {
		return ResponseEntity.ok(adminService.updateAdmin(admin));
	}

}
