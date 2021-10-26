package com.hiddenlake.it.integral.services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import com.hiddenlake.it.integral.models.Asistencia;
import com.hiddenlake.it.integral.models.Persona;
import com.hiddenlake.it.integral.repositories.AsistenciasRepository;

@Service
public class AsistenciaServiceImpl extends ServiceImpl<Asistencia, Long> {

	@Autowired
	private AsistenciasRepository asistenciaRepository;

	@Override
	public Asistencia create(Asistencia entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}

	@Override
	public void delete(Asistencia entity) {
		// TODO Auto-generated method stub
		super.delete(entity);
	}

	@Override
	public Asistencia update(Asistencia entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	@Override
	public Asistencia findById(Long id) {
		// TODO Auto-generated method stub
		return super.findById(id);
	}

	@Override
	public List<Asistencia> findAll() {
		return super.findAll();
	}

	public List<Asistencia> findBeetwenDates(@Nullable Date startDate, @Nullable Date endDate, Long idPersona) {
		if (startDate == null) {
			Calendar c = Calendar.getInstance(); // this takes current date
			c.set(Calendar.MONTH, c.MONTH);
			c.set(Calendar.DAY_OF_MONTH, 23);
			startDate = c.getTime();
		}
		endDate = (endDate == null) ? endDate = Calendar.getInstance().getTime() : endDate;
		/*
		 * if (endDate == null) { endDate = Calendar.getInstance().getTime(); }
		 */
		System.out.println("StartDate: " + startDate + " endDate: " + endDate + " idPersona: " + idPersona.intValue());
		return this.asistenciaRepository.findByDateBetweenFromPerson(startDate, endDate, idPersona.intValue());
	}

	public List<Persona> findBeetwenDatesFromArea(@Nullable Date startDate, @Nullable Date endDate, Long idArea) {
		if (startDate == null) {
			Calendar c = Calendar.getInstance(); // this takes current date
			c.set(Calendar.MONTH, c.MONTH);
			c.set(Calendar.DAY_OF_MONTH, 23);
			startDate = c.getTime();
		}
		endDate = (endDate == null) ? endDate = Calendar.getInstance().getTime() : endDate;
		/*
		 * if (endDate == null) { endDate = Calendar.getInstance().getTime(); }
		 */
		System.out.println("StartDate: " + startDate + " endDate: " + endDate + " idArea: " + idArea.intValue());
		return this.asistenciaRepository.findByDateBetweenFromArea(startDate, endDate, idArea.intValue());
	}

	public void setIntervalDateAssistante(Asistencia asistencia, Date date) {
		while (asistencia.getFecha().getTime() <= date.getTime()) {
			Asistencia asist = new Asistencia();
			asist.setFecha(asistencia.getFecha());
			asist.setPersona(asistencia.getPersona());
			asist.setTipoAsistencia(asistencia.getTipoAsistencia());
			super.create(asist);
			Date fechita = new Date();
			fechita.setTime((long) asistencia.getFecha().getTime() + (86400000));
			asistencia.setFecha(fechita);
		}
	}

	public void setMultiDateAssistante(Asistencia[] asistencia) {
		int w = 0;
		while (w < asistencia.length) {
			System.out.println(asistencia[w]);
			super.create(asistencia[w]);
			w++;
		}
	}

}
