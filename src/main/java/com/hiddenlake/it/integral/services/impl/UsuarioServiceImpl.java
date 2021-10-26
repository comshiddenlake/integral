package com.hiddenlake.it.integral.services.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hiddenlake.it.integral.models.Usuario;
import com.hiddenlake.it.integral.repositories.UsuarioRepository;

@Service
public class UsuarioServiceImpl extends ServiceImpl<Usuario, Long> {

	private static final Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);

	@Autowired
	private UsuarioRepository userRepository;

	@Override
	public Usuario create(Usuario entity) {
		return super.create(entity);
	}

	@Override
	public void delete(Usuario entity) {
		super.delete(entity);
	}

	@Override
	public Usuario update(Usuario entity) {
		try {

			if (userRepository.findUsermail(entity.getEmail()).getId() != entity.getId()) {
				throw new EntityNotFoundException("Email is already register");
			}

			if (userRepository.findById(entity.getId()) == null) {
				logger.warn("PUT | Persona no existente.");
				throw new EntityNotFoundException("User not found");
			}

			logger.info("PUT | Persona actualizado: " + userRepository.findById(entity.getId()));
			super.update(entity);
			return userRepository.getOne(entity.getId());
		} catch (EntityNotFoundException e) {
			throw new EntityNotFoundException("Persona not updated, error: " + e.getMessage());
		}
	}

	@Override
	public Usuario findById(Long aLong) throws EntityNotFoundException {
		if (!(userRepository.findById(aLong).isPresent())) {
			throw new EntityNotFoundException("User not found with id:" + aLong);
		}
		return super.findById(aLong);
	}

	@Override
	public List<Usuario> findAll() throws EntityNotFoundException {
		return super.findAll();
	}

	@Override
	public void deleteById(Long aLong) {
		super.deleteById(aLong);
	}

	public List<Usuario> findAllByNombreAndApellido(String nombre) {
		return userRepository.findAllByNombreAndApellido(nombre);
	}

	public Optional<Usuario> findByUsernameOrEmail(String usernameOrEmail, String string) {
		return userRepository.findByUsernameOrEmail(usernameOrEmail, string);
	}

	public Usuario findByUsername(String username) {
		return userRepository.findUsername(username);
	}

	public boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}
}