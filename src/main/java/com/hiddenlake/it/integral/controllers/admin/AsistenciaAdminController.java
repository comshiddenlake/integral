package com.hiddenlake.it.integral.controllers.admin;

import java.util.Date;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hiddenlake.it.integral.models.Asistencia;
import com.hiddenlake.it.integral.services.impl.AsistenciaServiceImpl;
import com.hiddenlake.it.integral.services.impl.AsistenciaTipoServiceImpl;
import com.hiddenlake.it.integral.services.impl.PersonaServiceImpl;

@RestController
@RequestMapping("/api/admin/asistencias")
@Secured({"ROLE_ADMIN"})
public class AsistenciaAdminController {

	private static final Logger logger = LoggerFactory.getLogger(AsistenciaAdminController.class);

	@Autowired
	private AsistenciaServiceImpl asistenciaServiceImpl;

	@Autowired
	private PersonaServiceImpl personaServiceImpl;
	
	@Autowired
	private AsistenciaTipoServiceImpl tipoAsistenciaServiceImpl;

	@GetMapping
	public ResponseEntity<?> responseGet(/* @CurrentUser UserPrincipal currentUser */) throws EntityNotFoundException {
		logger.info("GET | Asistencias FindAll: " + asistenciaServiceImpl.findAll());
		return ResponseEntity.ok(asistenciaServiceImpl.findAll());
	}

	@GetMapping("/{idAsistencia}")
	public ResponseEntity<?> getById(@PathVariable("idAsistencia") Long idAsistencia) {
		logger.info("GET | GetPet: " + idAsistencia);
		return ResponseEntity.ok().body(asistenciaServiceImpl.findById(idAsistencia.longValue()));
	}

	@GetMapping("/persona/{idPersona}")
	public ResponseEntity<?> getByPersonaId(@PathVariable("idPersona") Long idPersona) {
		logger.info("GET | GetAsistenciaByPersona: " + idPersona);
		return ResponseEntity.ok().body(personaServiceImpl.findById(idPersona.longValue()).getAsistencia());
	}

	@PostMapping
	public ResponseEntity<?> addTipoAsistencia(@RequestBody Asistencia asistencia) {
		logger.info("POST | TipoAsistencia addTipoAsistencia: " + asistencia);
		return ResponseEntity.ok().body(asistenciaServiceImpl.create(asistencia));
	}

	@PostMapping("/interval")
	public ResponseEntity<?> setAssistanceInterval(
			@RequestParam("date") @DateTimeFormat(pattern = "dd-MM-yyyy") Date date,
			@RequestBody Asistencia asistencia) {
		logger.info("Asistencia Controller | setAssistanceInterval date:" + date + '\n' + "asistencia:" + asistencia);
		this.asistenciaServiceImpl.setIntervalDateAssistante(asistencia, date);
		return ResponseEntity.status(HttpStatus.OK).body("Re piola guachi");
	}

	@PostMapping("/multi/{idPersona}/{idTipoAsistencia}")
	public ResponseEntity<?> setAssistanceMulti(@RequestBody Asistencia[] asistencia, @PathVariable("idPersona") Long idPersona,
			@PathVariable("idTipoAsistencia") Long idTipoAsistencia) {
		logger.info("Asistencia Controller | setAssistanceInterval \n date:" + asistencia[0].toString() + '\n' + "asistencia:" + idPersona
				+ "tipo" + idTipoAsistencia);
		for (int i = 0; i<asistencia.length;i++) {
			asistencia[i].setPersona(this.personaServiceImpl.findById(idPersona));
			asistencia[i].setTipoAsistencia(this.tipoAsistenciaServiceImpl.findById(idTipoAsistencia));
			System.out.println(asistencia[i].toString());
		}
		this.asistenciaServiceImpl.setMultiDateAssistante(asistencia);
		return ResponseEntity.status(HttpStatus.OK).body("Re piola guachi");
	}

	@PutMapping
	public ResponseEntity<?> updateAsistenciaTipo(@RequestBody Asistencia asistencia) {
		try {
			logger.info("ADMIN PUT | User insertUser:" + asistencia);
			return ResponseEntity.ok().body(asistenciaServiceImpl.update(asistencia));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@DeleteMapping("/{idFeriado}")
	public ResponseEntity<?> deleteUser(@PathVariable("idFeriado") Long idFeriado) {
		try {
			asistenciaServiceImpl.deleteById(idFeriado);
			return ResponseEntity.ok().body("Usuario borrado");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

}
