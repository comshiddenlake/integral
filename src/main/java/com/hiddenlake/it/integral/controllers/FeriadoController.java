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

import com.hiddenlake.it.integral.models.Feriados;
import com.hiddenlake.it.integral.services.impl.FeriadoServiceImpl;

@RestController
@RequestMapping("/api/feriados")
public class FeriadoController {

	private static final Logger logger = LoggerFactory.getLogger(AsistenciaController.class);

	@Autowired
	private FeriadoServiceImpl feriadoServiceImpl;

	@GetMapping
	public ResponseEntity<?> responseGet(/* @CurrentUser UserPrincipal currentUser */) throws EntityNotFoundException {
		logger.info("GET | Asistencias FindAll: " + feriadoServiceImpl.findAll());
		return ResponseEntity.ok(feriadoServiceImpl.findAll());
	}

	@GetMapping("/{idFeriado}")
	public ResponseEntity<?> getById(@PathVariable("idFeriado") Long idFeriado) {
		logger.info("GET | GetPet: " + idFeriado);
		return ResponseEntity.ok().body(feriadoServiceImpl.findById(idFeriado.longValue()));
	}

	@PostMapping
	public ResponseEntity<?> addTipoAsistencia(@RequestBody Feriados feriados) {
		logger.info("POST | TipoAsistencia addTipoAsistencia: " + feriados);
		return ResponseEntity.ok().body(feriadoServiceImpl.create(feriados));
	}

	@PutMapping
	public ResponseEntity<?> updateAsistenciaTipo(@RequestBody Feriados feriados) {
		try {
			logger.info("ADMIN PUT | User insertUser:" + feriados);
			return ResponseEntity.ok().body(feriadoServiceImpl.update(feriados));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@DeleteMapping("/{idFeriado}")
	public ResponseEntity<?> deleteUser(@PathVariable("idFeriado") Long idFeriado) {
		try {
			feriadoServiceImpl.deleteById(idFeriado);
			return ResponseEntity.ok().body("Usuario borrado");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
