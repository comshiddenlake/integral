package com.hiddenlake.it.integral.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "asistencias", uniqueConstraints = { @UniqueConstraint(columnNames = { "fecha", "persona_id","tipo_asistencia_id" }) })
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "persona" })
public class Asistencia implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private @Id @GeneratedValue Long id;

	@NotNull
	@Temporal(TemporalType.DATE)
	@JsonFormat(timezone = "GMT-03:00")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "fecha")
	private Date fecha;

	@NotNull
	//@ToString.Exclude
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tipo_asistencia_id")
	private TipoAsistencia tipoAsistencia;

	@NotNull
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "persona_id")
	private Persona persona;
/*
	@Override
	public String toString() {
		return "id=" + id + ", fecha=" + fecha + ", tipoAsistencia=" + tipoAsistencia;
	}*/
	
	
}
