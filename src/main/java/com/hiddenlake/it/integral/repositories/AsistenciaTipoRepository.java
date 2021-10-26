package com.hiddenlake.it.integral.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hiddenlake.it.integral.models.TipoAsistencia;

@Repository
public interface AsistenciaTipoRepository extends JpaRepository<TipoAsistencia, Long> {

}
