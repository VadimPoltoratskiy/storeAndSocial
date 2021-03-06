package com.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.demo.service.CustomUserDetailsService;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = CustomUserDetailsService.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired 
	 private UserDetailsService userDetailsService;
	 
	@Autowired
	 public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {    
		 auth.userDetailsService(userDetailsService).passwordEncoder(passwordencoder());
	 }
	 

	 
	 @Override
	 protected void configure(HttpSecurity http) throws Exception {
	   http.authorizeRequests()
	  .antMatchers("/worker/welcome").access("hasRole('ROLE_ADMIN')")
	  .anyRequest().permitAll()
	  .and()
	    .formLogin().loginPage("/worker/login")
	    .usernameParameter("username").passwordParameter("password")
	    .defaultSuccessUrl("/worker/welcome")
	  .and()
	   .logout().logoutSuccessUrl("/worker/")	 
	   .and()
	   .exceptionHandling().accessDeniedPage("/worker/403")
	  .and()
	    .csrf();
	 }
	
	
	 
	 @Bean(name="passwordEncoder")
	    public PasswordEncoder passwordencoder(){
	     return new BCryptPasswordEncoder();
	    }
}
