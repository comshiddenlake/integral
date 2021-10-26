package com.hiddenlake.it.integral.services.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.hiddenlake.it.integral.models.Role;

@Service
public class RolServiceImpl extends ServiceImpl<Role, Long> {

	@Override
	public Role create(Role entity) {
		return super.create(entity);
	}

	@Override
	public Role update(Role entity) {
		return super.update(entity);
	}

	public Role findById(Long id) throws EntityNotFoundException {
		return super.findById(id);
	}

	@Override
	public List<Role> findAll() throws EntityNotFoundException {
		return super.findAll();
	}


}