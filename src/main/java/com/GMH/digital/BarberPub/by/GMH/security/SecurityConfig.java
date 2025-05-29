package com.GMH.digital.BarberPub.by.GMH.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    return http
	        .csrf(csrf -> csrf.disable())
	        .headers(headers -> headers.frameOptions(frame -> frame.disable()))
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/h2-console/**").permitAll()
	            .requestMatchers("/admin/**").hasRole("ADMIN")
	            .requestMatchers("/user/**").hasRole("USER")
	            .anyRequest().authenticated()
	        )
	        .formLogin(login -> login
	            .permitAll()
	        )
	        .build();
	}

}
