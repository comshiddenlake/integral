package com.hiddenlake.it.integral.controllers;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hiddenlake.it.integral.models.Persona;
import com.hiddenlake.it.integral.security.CurrentUser;
import com.hiddenlake.it.integral.security.UserPrincipal;
import com.hiddenlake.it.integral.services.impl.PersonaServiceImpl;

@RestController
@RequestMapping("/api/persona")
public class PersonaController {

	private static final Logger logger = LoggerFactory.getLogger(PersonaController.class);

	@Autowired
	private PersonaServiceImpl personaServiceImpl;

	@GetMapping
	public ResponseEntity<?> responseGet(@CurrentUser UserPrincipal currentUser) throws EntityNotFoundException {
		System.out.println("GET Persona - currentUser: " + currentUser.toString());
		logger.info("GET | Persona FindAll: " + personaServiceImpl.findAllFromAreaId(currentUser.getId()));
		return ResponseEntity.ok(personaServiceImpl.findAllFromAreaId(currentUser.getId()));
	}

	@GetMapping("/{idPersona}")
	public ResponseEntity<?> getById(@PathVariable("idPersona") Long idPersona) {
		logger.info("GET | GetPet: " + idPersona);
		return ResponseEntity.ok().body(personaServiceImpl.findById(idPersona.longValue()));
	}

	@PostMapping
	public ResponseEntity<?> addTipoAsistencia(@RequestBody Persona persona) {
		logger.info("POST | TipoAsistencia addTipoAsistencia: " + persona);
		return ResponseEntity.ok().body(personaServiceImpl.create(persona));
	}

	@PutMapping
	public ResponseEntity<?> updateAsistenciaTipo(@RequestBody Persona persona) {
		try {
			logger.info("PUT | User insertUser:" + persona);
			return ResponseEntity.ok().body(personaServiceImpl.update(persona));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@DeleteMapping("/{idPersona}")
	public ResponseEntity<?> deleteUser(@PathVariable("idPersona") Long idPersona) {
		try {
			//personaServiceImpl.deleteById(idPersona);
			return ResponseEntity.ok().body("Usuario borrado");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

}
