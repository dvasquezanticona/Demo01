package com.prueba.app.user.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/*
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.prueba.app.user.dao.UserRepository;
import com.prueba.model.User;

/**
 * Esta clase es un servicio 
 * */

@Service
public class UserServiceImp implements UserService {
	
	private static final Logger log=(Logger) LogManager.getLogger(UserServiceImp.class);

	@Autowired
	protected UserRepository userRepository;

	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		log.info( "Metodo save().",this.getClass().getName());
		return this.userRepository.save(user);
		//return null;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		log.info( "Metodo findAll().",this.getClass().getName());
		return this.userRepository.findAll();
	}

	@Override
	public void deleteUser(Long id) throws Exception {
		// TODO Auto-generated method stub
		try{
			log.info( "Metodo deleteUser().",this.getClass().getName());
			this.userRepository.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			log.error( "Ocurrio un error al eliminar un usuario. " + e.getMessage(),this.getClass().getName());
			throw new Exception("La entidad con id " + id + " no exite en bbdd.");
		}
	}

	

}
