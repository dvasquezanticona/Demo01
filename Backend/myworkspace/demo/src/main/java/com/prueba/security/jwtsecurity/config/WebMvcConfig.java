package com.prueba.security.jwtsecurity.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final long MAX_AGE_SECS = 3600;
    private static final Logger log=(Logger) LogManager.getLogger(WebMvcConfig.class);
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
    	log.info("Metodo addCorsMappings(). Agregando maping, origen, metodos y timeOut al CorsRegistry.",this.getClass().getName());
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
                .maxAge(MAX_AGE_SECS);
    }
}