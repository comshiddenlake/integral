package com.hiddenlake.it.integral.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hiddenlake.it.integral.models.Area;

@Service
public class AreaServiceImpl extends ServiceImpl<Area, Long> {
	
	@Override
	public Area create(Area entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}

	@Override
	public void delete(Area entity) {
		// TODO Auto-generated method stub
		super.delete(entity);
	}

	@Override
	public Area update(Area entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	@Override
	public Area findById(Long id) {
		// TODO Auto-generated method stub
		return super.findById(id);
	}

	@Override
	public List<Area> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}

}
