package com.hiddenlake.it.integral.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import com.hiddenlake.it.integral.models.Asistencia;
import com.hiddenlake.it.integral.models.Persona;

@Repository
public interface AsistenciasRepository extends JpaRepository<Asistencia, Long> {

	@Query(value = "SELECT * from asistencias a WHERE a.persona_id = :idPersona and a.fecha between :startDate and :endDate", nativeQuery = true)
	List<Asistencia> findByDateBetweenFromPerson(@Temporal(TemporalType.DATE) @Param("startDate") Date startDate,
			@Temporal(TemporalType.DATE) @Param("endDate") Date endDate, @Param("idPersona") Integer idPersona);

	// Acomodar el select para que traiga los registros conforme al id desde el area
	// y no desde la persona
	@Query(value = "SELECT * FROM persona Persona join asistencias Asist on Persona.id = Asist.persona_id and Persona.area_id =:idArea where Asist.fecha between :startDate AND :endDate", nativeQuery = true)
	List<Persona> findByDateBetweenFromArea(@Temporal(TemporalType.DATE) @Param("startDate") Date startDate,
			@Temporal(TemporalType.DATE) @Param("endDate") Date endDate, @Param("idArea") Integer idArea);

	/*
	 * EJEMPLO
	 * 
	 * SELECT Asist.id,Asist.fecha, Asist.tipo_asistencia_id, Persona.nombre AS
	 * Nombre, Persona.apellido AS Apellido FROM asistencias Asist JOIN persona
	 * Persona ON Asist.persona_id = Persona.id AND Persona.area_id = 0 WHERE
	 * Asist.fecha BETWEEN '2010-10-10' AND '2021-10-10';
	 *
	 *
	 * List<Employee>
	 * findByJoinedDateBetween(@Temporal(TemporalType.DATE) @Param("startDate") Date
	 * startDate,@Temporal(TemporalType.DATE) @Param("endDate")Date endDate);
	 * 
	 * @Query("SELECT e from Employee e where e.joinedDate between :startDate and :endDate"
	 * )
	 * 
	 * List<Employee>
	 * findByJoinedDate(@Temporal(TemporalType.DATE) @Param("startDate") Date
	 * startDate, @Temporal(TemporalType.DATE) @Param("endDate") Date endDate);
	 * 
	 * @Query(value =
	 * "SELECT * from Employee e where e.joinedDate between :startDate and :endDate"
	 * , nativeQuery = true)
	 * 
	 * List<Employee>
	 * findByJoinedDateBetweenNative(@Temporal(TemporalType.DATE) @Param(
	 * "startDate") Date
	 * startDate,@Temporal(TemporalType.DATE) @Param("endDate")Date endDate);
	 */

	@Query(value = "SELECT * FROM integral.asistencias u WHERE u.fecha='fecha' AND u.persona_id='personaid'", nativeQuery = true)
	List<Asistencia> findByUserAndAssistance(
			@DateTimeFormat(pattern = "yyyy-MM-dd") @Temporal(TemporalType.DATE) @Param("fecha") Date fecha,
			@Param("personaid") Long personaid);
}
