package com.hiddenlake.it.integral.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hiddenlake.it.integral.models.Feriados;

@Service
public class FeriadoServiceImpl extends ServiceImpl<Feriados, Long> {

	@Override
	public Feriados create(Feriados entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}

	@Override
	public void delete(Feriados entity) {
		// TODO Auto-generated method stub
		super.delete(entity);
	}

	@Override
	public Feriados update(Feriados entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	@Override
	public Feriados findById(Long id) {
		// TODO Auto-generated method stub
		return super.findById(id);
	}

	@Override
	public List<Feriados> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}

}