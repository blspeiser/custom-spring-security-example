package net.cambium.examples.rest.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * SecurityConfiguration.
 *
 *  Spring Security configuration for Spring Boot that 
 *  will allow us to use a custom authorization mechanism.
 *  
 * @author Baruch Speiser, Cambium.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    //Use our custom security filter instead of username/password authentication
    http.addFilterAt(
        new CustomAuthorizationFilter(), 
        UsernamePasswordAuthenticationFilter.class);
  }
  
}
