package com.r2s.javabackend09.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, UserDetailsService userDetailsService,
			JwtAuthFilter filter) throws Exception {
		return httpSecurity
				.csrf().disable()
				.authorizeHttpRequests()
				.requestMatchers("/variantproducts/update/**","/variantproducts/addproduct/**","admin/products/**","/delete/{user_id}/addresses","/update/{user_id}/addresses","/get/{user_id}/addresses","/add/{user_id}/addresses","/users/register-user", "/api/auth/**","/category","/products/**","variantproducts/**", "/api/auth/getuserlogin/**")
				.permitAll()
				.and()
				.authorizeHttpRequests()
				.requestMatchers( /*cho bat ki truy cap nao mien la /product*/ 
						"/profiles" /*chi duy nhat /profiles, /profiles/1.. cung k duoc*/)
				.hasAnyRole("ADMIN", "USER")
				.and()
//				.authorizeHttpRequests()
//				.requestMatchers("/users/findById")
//				.hasRole("ADMIN")
//				.and()
				.authorizeHttpRequests()
				.anyRequest()
				.authenticated()
				.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authenticationProvider(authenticationProvider(userDetailsService))
				.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class).build();
	}
}
