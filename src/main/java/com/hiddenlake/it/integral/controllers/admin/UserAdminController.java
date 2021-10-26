package com.hiddenlake.it.integral.controllers.admin;

import java.util.Collections;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hiddenlake.it.integral.models.Role;
import com.hiddenlake.it.integral.models.RoleName;
import com.hiddenlake.it.integral.models.Usuario;
import com.hiddenlake.it.integral.repositories.RoleRepository;
import com.hiddenlake.it.integral.services.impl.UsuarioServiceImpl;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@RestController
@RequestMapping("/api/admin/users")
@Secured({"ROLE_ADMIN"})
public class UserAdminController {

	private static final Logger logger = LoggerFactory.getLogger(UserAdminController.class);

	@Autowired
	private UsuarioServiceImpl userServiceImpl;
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@GetMapping
	public ResponseEntity<?> listUser() throws EntityNotFoundException {
		logger.info("ADMIN GET | Users listUser()");
		if (userServiceImpl.findAll().isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Contenido no encontrado");
		} else {
			return ResponseEntity.ok().body(userServiceImpl.findAll());
		}
	}

	@GetMapping("/{idUser}")
	public ResponseEntity<?> getUserById(@PathVariable("idUser") Long idUser) throws EntityNotFoundException {
		logger.info("ADMIN GET | User getUserById(): " + idUser);
		return ResponseEntity.ok().body(userServiceImpl.findById(idUser));
	}
	/*
	@GetMapping("/pets/{idUser}")
	public ResponseEntity<?> getPetsByUserId(@PathVariable("idUser") Long idUser) throws EntityNotFoundException {
		logger.info("ADMIN GET | User getPetsByUserId(): " + idUser);
		return ResponseEntity.ok().body(userServiceImpl.findById(idUser).getPets());
	}
*/
	@PostMapping
	public ResponseEntity<?> addUser(@RequestBody Usuario user) throws Exception {
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		Role userRole = roleRepository.findByName(RoleName.ROLE_REF)
				.orElseThrow(() -> new Exception("User Role not set."));

		user.setRoles(Collections.singleton(userRole));
		logger.info("ADMIN POST | User addUser: " + user);
		return ResponseEntity.ok().body(userServiceImpl.create(user));
	}

	@PostMapping("/search")
	public ResponseEntity<?> findUser(@RequestBody String string) throws EntityNotFoundException {
		logger.info("ADMIN GET | User search: " + string);
		return ResponseEntity.ok().body(userServiceImpl.findByUsernameOrEmail(string, string));
	}

	@PutMapping
	public ResponseEntity<?> insertUser(@RequestBody Usuario user) {
		try {
			logger.info("ADMIN PUT | User insertUser:" + user);
			user.setPassword(userServiceImpl.findById(user.getId()).getPassword());
			return ResponseEntity.ok().body(userServiceImpl.update(user));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@DeleteMapping("/{idUser}")
	public ResponseEntity<?> deleteUser(@PathVariable("idUser") Long idUser) {
		try {
			userServiceImpl.deleteById(idUser);
			return ResponseEntity.ok().body("Usuario borrado");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
