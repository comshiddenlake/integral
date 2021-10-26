package com.hiddenlake.it.integral.controllers.admin;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hiddenlake.it.integral.models.Role;
import com.hiddenlake.it.integral.services.impl.RolServiceImpl;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@RestController
@RequestMapping("/api/admin/roles")
@Secured({"ROLE_ADMIN"})
public class RoleAdminController {

	@Autowired
	private RolServiceImpl rolServiceImpl;

	@GetMapping
	public List<Role> listRole() throws EntityNotFoundException {
		return rolServiceImpl.findAll();
	}
}
