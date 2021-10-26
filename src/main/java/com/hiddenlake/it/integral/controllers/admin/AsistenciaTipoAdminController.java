package com.hiddenlake.it.integral.controllers.admin;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hiddenlake.it.integral.models.TipoAsistencia;
import com.hiddenlake.it.integral.services.impl.AsistenciaServiceImpl;
import com.hiddenlake.it.integral.services.impl.AsistenciaTipoServiceImpl;

@RestController
@RequestMapping("/api/admin/asistencias_tipo")
@Secured({"ROLE_ADMIN"})
public class AsistenciaTipoAdminController {

	private static final Logger logger = LoggerFactory.getLogger(AsistenciaAdminController.class);

	@Autowired
	private AsistenciaTipoServiceImpl asistenciaTipoServiceImpl;

	@GetMapping
	public ResponseEntity<?> responseGet(/* @CurrentUser UserPrincipal currentUser */) throws EntityNotFoundException {
		logger.info("GET | Asistencias FindAll: " + asistenciaTipoServiceImpl.findAll());
		return ResponseEntity.ok(asistenciaTipoServiceImpl.findAll());
	}

	@GetMapping("/{idAsistenciaTipo}")
	public ResponseEntity<?> getById(@PathVariable("idAsistenciaTipo") Long idAsistenciaTipo) {
		logger.info("GET | GetPet: " + idAsistenciaTipo);
		return ResponseEntity.ok().body(asistenciaTipoServiceImpl.findById(idAsistenciaTipo.longValue()));
	}

	@PostMapping
	public ResponseEntity<?> addTipoAsistencia(@RequestBody TipoAsistencia tipoAsistencia) {
		logger.info("POST | TipoAsistencia addTipoAsistencia: " + tipoAsistencia);
		return ResponseEntity.ok().body(asistenciaTipoServiceImpl.create(tipoAsistencia));
	}

	@PutMapping
	public ResponseEntity<?> updateAsistenciaTipo(@RequestBody TipoAsistencia asistenciaTipo) {
		try {
			logger.info("ADMIN PUT | User insertUser:" + asistenciaTipo);
			return ResponseEntity.ok().body(asistenciaTipoServiceImpl.update(asistenciaTipo));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@DeleteMapping("/{idAsistenciaTipo}")
	public ResponseEntity<?> deleteUser(@PathVariable("idAsistenciaTipo") Long idAsistenciaTipo) {
		try {
			asistenciaTipoServiceImpl.deleteById(idAsistenciaTipo);
			return ResponseEntity.ok().body("Usuario borrado");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

}
