package com.hiddenlake.it.integral.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "persona")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Persona implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private @Id Long id;

	@NotNull
	@Column(name = "nombre")
	private String nombre;

	@NotNull
	@Column(name = "apellido")
	private String apellido;

	@NotNull
	@Temporal(TemporalType.DATE)
	@JsonFormat(timezone = "GMT-03:00")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "fecha_nacimiento")
	private Date fechaNacimiento;

	@NotNull
	@Temporal(TemporalType.DATE)
	@JsonFormat(timezone = "GMT-03:00")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "inicio_actividad")
	private Date inicioActividad;

	@Nullable
	@Temporal(TemporalType.DATE)
	@JsonFormat(timezone = "GMT-03:00")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "fin_actividad")
	private Date finActividad;

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "area_id")
	private Area area;

	// @JsonProperty
	@Getter(onMethod = @__(@JsonProperty))
	@Setter(onMethod = @__(@JsonIgnore))
	@OneToMany(mappedBy = "persona", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Asistencia> asistencia;

}
