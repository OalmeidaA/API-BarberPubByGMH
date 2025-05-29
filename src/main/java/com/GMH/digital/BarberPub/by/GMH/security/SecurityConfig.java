package com.GMH.digital.BarberPub.by.GMH.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.core.userdetails.User;

@Configuration
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf(csrf -> csrf.disable())
	        .headers(headers -> headers.frameOptions(frame -> frame.disable()))
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/h2-console/**").permitAll()
	            .requestMatchers("/Client").permitAll()
	            .requestMatchers("/admin/**").hasRole("ADMIN")
	            .requestMatchers("/user/**").hasRole("USER")
	            .anyRequest().authenticated()
	        )
	        .httpBasic();

	    // Retorna o SecurityFilterChain criado a partir do HttpSecurity
	    return http.build();
	}
	
	
	@Bean
	public UserDetailsService userDetailsService() {
	    UserDetails admin = User
	        .withUsername("admin")
	        .password("{noop}123456") // {noop} = sem encoding (somente para testes!)
	        .roles("ADMIN")
	        .build();

	    UserDetails user = User
	        .withUsername("user")
	        .password("{noop}123456")
	        .roles("USER")
	        .build();

	    return new InMemoryUserDetailsManager(admin, user);
	}
	

}
