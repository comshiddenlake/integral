package com.hiddenlake.it.integral.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "tipo_asistencias")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class TipoAsistencia implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private @Id @GeneratedValue Long id;
	
	@NotNull
	@JsonInclude
	@Column(name = "tipo_asistencia")
	private String tipoAsistencia;
	
}
