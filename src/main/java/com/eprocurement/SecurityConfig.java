package com.eprocurement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.eprocurement.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private AdminProperties adminProperties;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	//TODO configure
	@Override
	protected void configure(HttpSecurity http) throws Exception {	
		http
			.formLogin()
			.and()
			.authorizeRequests()
				.antMatchers("/js/**","/bootstrap/**","/jquery-3.2.1").permitAll()
				.antMatchers("/item/new","/item/all","/api/items","/item/save","/pr/new", "/api/pritems/**").hasAnyRole("ADMIN","USER")
				.anyRequest().hasRole("ADMIN");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser(adminProperties.getUsername())
				.password(adminProperties.getPassword())
				.roles("ADMIN")
				.disabled(adminProperties.isDisabled())
				.and()
			.and()
			.userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder());		
	}		
}