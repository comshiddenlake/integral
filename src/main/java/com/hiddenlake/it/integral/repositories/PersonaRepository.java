package com.hiddenlake.it.integral.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hiddenlake.it.integral.models.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

	@Query("SELECT u FROM Persona u WHERE u.area=id")
	List<Persona> findUsernames(@Param("id") String id);
}
