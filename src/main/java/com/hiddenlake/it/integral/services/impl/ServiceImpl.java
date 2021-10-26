package com.hiddenlake.it.integral.services.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hiddenlake.it.integral.services.Service;

public abstract class ServiceImpl<T, ID extends Serializable> implements Service<T, ID> {

	@Autowired
	protected JpaRepository<T, ID> dao;

	@Override
	public T create(T entity) throws RuntimeException {
		return dao.saveAndFlush(entity);
	}

	@Override
	public void delete(T entity) {
		dao.delete(entity);
		dao.flush();
	}

	@Override
	public void deleteById(ID id) {
		dao.deleteById(id);
		dao.flush();
	}

	@Override
	public T update(T entity) {
		return dao.saveAndFlush(entity);
	}

	@Override
	public T findById(ID id) {
		return dao.getOne(id);
	}

	@Override
	public List<T> findAll() {
		return dao.findAll();
	}
}