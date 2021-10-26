package com.hiddenlake.it.integral.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hiddenlake.it.integral.models.Persona;
import com.hiddenlake.it.integral.models.Usuario;
import com.hiddenlake.it.integral.repositories.AreaRepository;
import com.hiddenlake.it.integral.repositories.PersonaRepository;
import com.hiddenlake.it.integral.repositories.UsuarioRepository;

@Service
public class PersonaServiceImpl extends ServiceImpl<Persona, Long> {

	@Autowired
	private PersonaRepository personaRepository;
	
	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
	
	@Autowired
	private AsistenciaServiceImpl asistenciaServiceImpl;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private AreaServiceImpl areaServiceImpl;

	@Override
	public Persona create(Persona entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}

	@Override
	public void delete(Persona entity) {
		// TODO Auto-generated method stub
		super.delete(entity);
	}

	@Override
	public Persona update(Persona entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	@Override
	public Persona findById(Long id) {
		// TODO Auto-generated method stub
		return super.findById(id);
	}

	@Override
	public List<Persona> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}

	public List<Persona> findAllFromAreaId(Long currentUserId) {
		List<Persona> response = areaServiceImpl.findById(usuarioServiceImpl.findById(currentUserId).getArea().getId()).getPersonas();
		for (int i = 0; i < response.size();i++) {
			//response.get(i).setAsistencia(this.asistenciaServiceImpl.findBeetwenDates(null, null,response.get(i)));
		}
		return response;
	}

}