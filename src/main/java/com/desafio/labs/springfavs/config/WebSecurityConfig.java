package com.desafio.labs.springfavs.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
				.antMatchers("/*").permitAll().anyRequest().authenticated().and().httpBasic();
	}

	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		UserDetails user = User.withUsername("Luiza")
				.password("{bcrypt}$2a$10$nbDbLKMWiRDrmS9Lri8yoOlHGFnL/jS5KXazLV1F3J1TUSJycrb4C").roles("USER").build();

		return new InMemoryUserDetailsManager(user);
	}

	/**
	 * Gerador de senha
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String senhaAdmin = "Labs123";
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		System.out.println("senha = " + encoder.encode(senhaAdmin));
	}

}
