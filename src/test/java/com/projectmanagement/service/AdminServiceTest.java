package com.projectmanagement.service;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.projectmanagement.entity.Admin;
import com.projectmanagement.exceptions.ResourceNotFoundException;
import com.projectmanagement.repo.AdminRepo;


/*import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.projectmanagement.entity.Admin;
import com.projectmanagement.exceptions.ResourceNotFoundException;
import com.projectmanagement.repo.AdminRepo;
import com.projectmanagement.repo.EmployeeRepo;
import com.projectmanagement.service.AdminService;
import com.projectmanagement.service.EmployeeService;

@SpringBootTest*/
@SpringBootTest
class AdminServiceTest {
	@Mock
	private AdminRepo adminRepo;

	@InjectMocks
	private AdminServiceImpl adminService;

	@Test
	public void testAddAdmin() {
		Admin admin = new Admin();
		admin.setUserId(1L);
		admin.setUserName("admin");
		admin.setUserPassword("admin123");

		when(adminRepo.save(admin)).thenReturn(admin);

		Admin createdAdmin = adminService.addAdmin(admin);
		assertThat(createdAdmin.getUserName()).isEqualTo("admin");
	}
	@Test
	public void testUpdateAdmin() throws ResourceNotFoundException {
		Admin admin = new Admin();
		admin.setUserId(1L);
		admin.setUserName("admin");
		admin.setUserPassword("admin123");

		when(adminRepo.findById(admin.getUserId())).thenReturn(java.util.Optional.of(admin));
		when(adminRepo.save(admin)).thenReturn(admin);

		Admin updatedAdmin = adminService.updateAdmin(admin);
		assertThat(updatedAdmin.getUserPassword()).isEqualTo("admin123");
	}
	@Test
	public void testAuthenticateAdmin() throws Exception {
		Admin admin = new Admin();
		admin.setUserId(1L);
		admin.setUserName("admin");
		admin.setUserPassword("admin123");

		when(adminRepo.authenticateAdmin(admin.getUserName())).thenReturn(admin);

		Admin authenticatedAdmin = adminService.authenticateAdmin(admin.getUserName(), admin.getUserPassword());
		assertEquals(admin.getUserName(), authenticatedAdmin.getUserName());
	}
	@Test
	public void testAuthenticateAdmin_InvalidCredentialsException() throws Exception {
		when(adminRepo.authenticateAdmin("admin")).thenReturn(null);

		assertThrows(Exception.class, () -> adminService.authenticateAdmin("admin", "admin123"));
	}


	/*@Autowired
	private AdminService adminService;
	@Autowired
	private AdminRepo adminRepo;
	
	
	@Test
	  void testAddAdmin() {
		Admin admin = new Admin();
		admin.setUserId(1);
		admin.setUserName("admin");
		admin.setUserPassword("1234");
		admin.setRole("ADMIN");
		assertEquals(admin.toString(), adminService.addAdmin(admin).toString());
		adminRepo.delete(admin);

	}
	
	

	@Test
	void testUpdateAdmin() throws ResourceNotFoundException {
		Admin admin = new Admin();
		admin.setUserId(1);
		admin.setUserName("admin");
		admin.setUserPassword("1234");
		admin.setRole("ADMIN");

		adminRepo.save(admin);

		// update admin

		Admin uadmin = new Admin();
		uadmin.setUserId(1);
		uadmin.setUserName("admin");
		uadmin.setUserPassword("root");
		uadmin.setRole("ADMIN");
		assertEquals(uadmin.toString(), adminService.updateAdmin(uadmin).toString());
		adminRepo.delete(uadmin);
	}

	@Test
	void testAuthenticateAdmin() throws Exception {
		Admin admin = new Admin();
		admin.setUserId(1);
		admin.setUserName("admin");
		admin.setUserPassword("1234");
		admin.setRole("ADMIN");
		adminRepo.save(admin);
		assertEquals(admin.toString(), adminService.authenticateAdmin("admin", "1234").toString());
		adminRepo.delete(admin);
	}*/

}
