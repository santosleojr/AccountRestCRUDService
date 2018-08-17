package com.main.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication().withUser("testuser").password("{noop}testpass").roles("USER");
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception{
		http.csrf().disable().httpBasic().and().authorizeRequests()
		.antMatchers(HttpMethod.GET,"/**").hasRole("USER")
		.antMatchers(HttpMethod.POST,"/**").hasRole("USER")
		.antMatchers(HttpMethod.PUT,"/**").hasRole("USER")
		.antMatchers(HttpMethod.DELETE,"/**").hasRole("USER");
		
	}

}
