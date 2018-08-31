package com.prueba.app.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.model.User;


/**
 * Interfaz para los UserRepository extiende del JpaReposity
 * pasandole <User, Long>
 * */
public interface UserRepository extends JpaRepository<User, Long> {

	@SuppressWarnings("unchecked")
	User save(User user);
	
	User findByLogin(String login);
	
}
