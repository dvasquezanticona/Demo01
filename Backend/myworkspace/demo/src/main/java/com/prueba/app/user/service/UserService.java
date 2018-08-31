package com.prueba.app.user.service;


import java.util.List;

import com.prueba.model.User;

/**
 * Interface que define el UserService. 
 * Se definen los métodos para guardar (nuevo y modificar), seleccionar y eliminar
 * */
public interface UserService {

	User save(User user);

	List<User> findAll();

	void deleteUser(Long id) throws Exception;



}
