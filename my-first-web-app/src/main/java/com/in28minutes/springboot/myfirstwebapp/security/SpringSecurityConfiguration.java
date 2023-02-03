package com.in28minutes.springboot.myfirstwebapp.security;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;
@Configuration
public class SpringSecurityConfiguration {
	// LDAP or DATABASE
	// InMemory
	
//	InMemoryUserDetailsManager
	@Bean
	public InMemoryUserDetailsManager createUserDetailsManager() {
		
		
		UserDetails user1 = createNewUser("Rishu", "abc");
		UserDetails user2 = createNewUser("Sourav", "abc");
		
		return new InMemoryUserDetailsManager(user1,user2);
	}

	private UserDetails createNewUser(String username, String password) {
		Function<String, String> pass=input->passwordEncoder().encode(input);
		UserDetails user = User.builder()
				.passwordEncoder(pass)
				.username(username)
				.password(password)
				.roles("USER","ADMIN")
				.build();
		return user;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean 
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				auth->auth.anyRequest().authenticated()
				);
		http.formLogin(withDefaults());
		
		http.csrf().disable();
		http.headers().frameOptions().disable();
		return http.build();
	}
	
}
