package com.prueba.app.user.controller;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.app.user.service.UserService;
import com.prueba.model.User;
import com.prueba.util.RestResponse;

@RestController
public class UserController {

	private static final Logger log=(Logger) LogManager.getLogger(UserController.class);
	
	
	@Autowired
	protected UserService userService;
	
	//Objeto que convertira un json a clase java
	protected ObjectMapper mapper;
	
	@RequestMapping(value="/saveOrUpdate",method=RequestMethod.POST)
	public RestResponse saveOrUpdate(@RequestBody String userJson ) throws JsonParseException, JsonMappingException, IOException{
		log.info("Inicio del metodo saveOrUpdate().",this.getClass().getName() );
		//Aqui estamos convirtiendo un Json a Clase java.
		mapper=new ObjectMapper();
		User user=this.mapper.readValue(userJson,User.class);
		
		if (!validateUser(user)){
			//La clase RestResponse la hemos creado nosotros para 
			//devolver respuestas en la invocación.
			log.error("Los campos obligatorios no estan informados.",this.getClass().getName() );
			return new RestResponse(HttpStatus.NOT_ACCEPTABLE.value(), "Los campos obligatorios no estan informados.");
		}
		
		//Si pasa la validación invocamos al servicio
		this.userService.save(user);
		log.info("El registro se ha guardado con exito en bbdd.",this.getClass().getName() );
		
		//Retornamos el mensaje de exito
		return new RestResponse(HttpStatus.ACCEPTED.value(), "Operacion exitosa");
	}

	@RequestMapping(name="/getUsers", method=RequestMethod.GET)
	public List<User> getUsers(){
		log.info("Inicio del metodo getUsers()",this.getClass().getName() );
		return this.userService.findAll();
	} 
/*
	@RequestMapping(name="/deleteUser",method=RequestMethod.POST)
	public RestResponse deleteUser(@RequestBody String userJson) throws Exception{
		
		log.info("Inicio del metodo deleteUser()",this.getClass().getName() );
		mapper=new ObjectMapper();
		User user=this.mapper.readValue(userJson,User.class);
		
		if (user.getId()==null){
			log.error("El id es nulo",this.getClass().getName() );
			throw new Exception("El id es nulo.");
		}
		
		try{
			this.userService.deleteUser(user.getId());
		}catch(Exception e){
			log.error("Error al eliminar. " + e.getMessage(),this.getClass().getName() );
			return new RestResponse(HttpStatus.FAILED_DEPENDENCY.value(), "Error al eliminar." + e.getMessage());
		}
				
		//Retornamos el mensaje de exito
		log.info("Operación existosa.",this.getClass().getName() );
		return new RestResponse(HttpStatus.ACCEPTED.value(), "Operacion exitosa.");
	}
	*/
	
	/**
	 * Método para validar un objeto usuario.
	 * */
	private boolean validateUser(User user){
		boolean isValid=true;
		
		if (user.getFirstName()==null || user.getFirstName().isEmpty() ){
			isValid=false;
		}
		
		if (user.getFirstSurname()==null || user.getFirstSurname().isEmpty() ){
			isValid=false;
		}
		
		if (user.getAddress()==null || user.getAddress().isEmpty() ){
			isValid=false;
		}
		
		if (user.getLogin()==null || user.getLogin().isEmpty() ){
			isValid=false;
		}
		
		if (user.getPassword()==null || user.getPassword().isEmpty() ){
			isValid=false;
		}
		
		return isValid;
	}

}
