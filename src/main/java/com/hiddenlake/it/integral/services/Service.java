package com.hiddenlake.it.integral.services;

import java.io.Serializable;
import java.util.List;


public interface Service<T, ID extends Serializable> {

	T create(final T entity);

	void delete(final T entity);

	T update(final T entity);

	void deleteById(final ID id);

	T findById(final ID id);

	List<T> findAll();

}