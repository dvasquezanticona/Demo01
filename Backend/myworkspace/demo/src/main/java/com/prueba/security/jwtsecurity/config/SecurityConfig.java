package com.prueba.security.jwtsecurity.config;

//My imports
import com.prueba.security.jwtsecurity.CustomUserDetailsService;
import com.prueba.security.jwtsecurity.JwtAuthenticationEntryPoint;
import com.prueba.security.jwtsecurity.JwtAuthenticationFilter;

//Others imports
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final Logger log=(Logger) LogManager.getLogger(SecurityConfig.class);
	
    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {    	
    	log.info("Metodo configure(). Se está configurando el AuthenticationManagerBuilder.",this.getClass().getName());
        authenticationManagerBuilder
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
    	log.info("Retornando un objeto AuthenticationManager.",this.getClass().getName());
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
    	log.info("Retornando un objeto BCryptPasswordEncoder.",this.getClass().getName());
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	log.info("Configure. Se procede a realizar la configuración para HttpSecurity.",this.getClass().getName());
    	log.info("1. Se habilita el cors",this.getClass().getName());
    	log.info("2. Se deshabilita el csrf",this.getClass().getName());
    	log.info("3. Configuramos el exceptionHandling",this.getClass().getName());
    	log.info("4. Indicamos que la session será STATELESS",this.getClass().getName());
    	log.info("5. Indicamos las autorizaciones:",this.getClass().getName());
    	log.info("5.1. Se puede ver todos las imangenes, paginas html, hojas de estilos y js.",this.getClass().getName());
    	log.info("5.2. Solo se permite visualizar/utilizar los recursos que empiezan con /login/ y usen POST.",this.getClass().getName());
    	log.info("5.3. Para el resto de peticiones se necesita autorizaicón.",this.getClass().getName());
    	
        http
                .cors()
                    .and()
                .csrf()
                    .disable()
                .exceptionHandling()
                    .authenticationEntryPoint(unauthorizedHandler)
                    .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .authorizeRequests()
                    .antMatchers("/",
                        "/favicon.ico",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js")
                        .permitAll()
                    .antMatchers(HttpMethod.POST,"/login/**")
                        .permitAll()
                    /*.antMatchers("/api/user/checkUsernameAvailability", "/api/user/checkEmailAvailability")
                        .permitAll()
                    .antMatchers("/app/**")
                        .permitAll()*/
                     /*
                      *	
					//Any URL that starts with "/admin/" will be restricted to users who have the role "ROLE_ADMIN". You will notice that since we are invoking the hasRole method we do not need to specify the "ROLE_" prefix.
                     .antMatchers("/admin/**").hasRole("ADMIN")                                      
                     //Any URL that starts with "/db/" requires the user to have both "ROLE_ADMIN" and "ROLE_DBA". You will notice that since we are using the hasRole expression we do not need to specify the "ROLE_" prefix.
					.antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
                      * */
                    .anyRequest()
                        .authenticated();

        // Add our custom JWT security filter
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }
}