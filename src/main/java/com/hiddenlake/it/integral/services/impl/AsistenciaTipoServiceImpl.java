package com.hiddenlake.it.integral.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hiddenlake.it.integral.models.TipoAsistencia;

@Service
public class AsistenciaTipoServiceImpl extends ServiceImpl<TipoAsistencia, Long> {

	@Override
	public TipoAsistencia create(TipoAsistencia entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}

	@Override
	public void delete(TipoAsistencia entity) {
		// TODO Auto-generated method stub
		super.delete(entity);
	}

	@Override
	public TipoAsistencia update(TipoAsistencia entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	@Override
	public TipoAsistencia findById(Long id) {
		// TODO Auto-generated method stub
		return super.findById(id);
	}

	@Override
	public List<TipoAsistencia> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}

}
