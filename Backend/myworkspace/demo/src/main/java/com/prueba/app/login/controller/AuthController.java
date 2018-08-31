package com.prueba.app.login.controller;

import com.prueba.exception.AppException;
//import com.example.polls.model.Role;
import com.prueba.security.jwtsecurity.RoleName;
import com.prueba.model.User;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.app.login.ApiResponse;
import com.prueba.app.login.JwtAuthenticationResponse;
import com.prueba.app.login.LoginRequest;
import com.prueba.app.login.SignUpRequest;
//import com.example.polls.repository.RoleRepository;
import com.prueba.app.user.dao.UserRepository;
import com.prueba.security.jwtsecurity.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

import java.io.IOException;
import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

   // @Autowired
    //RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody String loginRequestJson) throws JsonParseException, JsonMappingException, IOException {

    	ObjectMapper mapper=new ObjectMapper();
		User loginRequest=mapper.readValue(loginRequestJson,User.class);
		String passworCodificada=passwordEncoder.encode(loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getLogin(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    /**
     * A este metodo se le enviará un objeto Usuario en formato
     * json.
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonParseException 
     * */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody String signUpRequestJson) throws JsonParseException, JsonMappingException, IOException {
    	
    	ObjectMapper mapper=new ObjectMapper();
		User user=mapper.readValue(signUpRequestJson,User.class);
		
        if(userRepository.findByLogin(user.getLogin()) ==null) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken bro!"),
                    HttpStatus.BAD_REQUEST);
        }
/*
        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }*/

        String passworCodificada=passwordEncoder.encode(user.getPassword());
        user.setPassword(passworCodificada);
/*
        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));
        user.setRoles(Collections.singleton(userRole));
*/
        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getLogin()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
    
    
}