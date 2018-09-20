package com.prueba.security.jwtsecurity;

import com.prueba.app.user.dao.UserRepository;
import com.prueba.model.User;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private static final Logger log=(Logger) LogManager.getLogger(CustomUserDetailsService.class);
	
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login)
            throws UsernameNotFoundException {
        // Let people login with either username or email
    	log.info("Buscando en base de datos el usuario del login: " + login);
        User user =  userRepository.findByLogin(login);
        if (user==null){
        	new UsernameNotFoundException("User not found with username or email : " + login);
        }
        log.info("Se ha encontrado el " + user.getLogin() + " en la bbdd.");
        return UserPrincipal.create(user);
    }

    // This method is used by JWTAuthenticationFilter
    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
            () -> new UsernameNotFoundException("User not found with id : " + id)
        );

        return UserPrincipal.create(user);
    }
}