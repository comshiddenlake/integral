package com.hiddenlake.it.integral.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hiddenlake.it.integral.models.Usuario;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {
	@Query("SELECT u FROM Usuario u WHERE u.username=:username")
	Usuario findUsername(@Param("username") String username);
	
	@Query("SELECT u FROM Usuario u WHERE u.email=:usermail") //Si anda, está verificado
	Usuario findUsermail(@Param("usermail") String usermail);
	
	@Query("SELECT u FROM Usuario u WHERE u.username=:username")
	 List<Usuario> findUsernames(@Param("username") String username);
	/*
	@Query("SELECT u FROM Usuario u WHERE u.email=:id")
	Optional<Usuario> findById(@Param("id") Long id);
	*/
	/*@Query("SELECT u FROM User u WHERE u.username=:username")
	Usuario findUsername(@Param("username") String username);

	@Query("SELECT u FROM User u WHERE u.email=:email")
	Usuario findUsermail(@Param("email") String email);*/

	//User findByDni(String dni);

	@Query("SELECT u FROM Persona u WHERE CONCAT(u.nombre, ' ', u.apellido) LIKE CONCAT('%',:name,'%')")
	List<Usuario> findAllByNombreAndApellido(@Param("name") String name);

	Optional<Usuario> findByEmail(String email);

	Optional<Usuario> findByUsernameOrEmail(String username, String email);

	List<Usuario> findByIdIn(List<Long> userIds);

	Optional<Usuario> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}