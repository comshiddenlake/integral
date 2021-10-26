package com.hiddenlake.it.integral.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Entity
@Table(name = "area")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Area implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private @Id @GeneratedValue Long id;

	@Column(name = "nombre")
	private String nombre;

	@Getter(onMethod = @__(@JsonIgnore))
	@Setter(onMethod = @__(@JsonProperty))
	@ToString.Exclude
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "referente_id")
	private Usuario referente;

	@Getter(onMethod = @__(@JsonIgnore))
	@Setter(onMethod = @__(@JsonProperty))
	@ToString.Exclude
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lider_id")
	private Usuario lider;

	// @Getter(onMethod = @__(@JsonProperty))
	// @Setter(onMethod = @__(@JsonIgnore))
	@ToString.Exclude
	@JsonIgnore
	@OneToMany(mappedBy = "area", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Persona> personas;
}
