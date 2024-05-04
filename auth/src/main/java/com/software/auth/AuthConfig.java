package com.software.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.Customizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;


@Configuration
@EnableWebSecurity
public class AuthConfig{
	
	@Configuration
	@Order(1)
	public static class ldapauth{
		@Bean
		public SecurityFilterChain securityfilterchain(HttpSecurity http) throws Exception {
			
			//http.authorizeHttpRequests((authorize) -> authorize.requestMatchers("/").fullyAuthenticated()).formLogin(Customizer.withDefaults());
			
			http.securityMatcher("/").csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests((authorize) -> authorize.requestMatchers("/").authenticated()).httpBasic(Customizer.withDefaults()).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
			
			
			return http.build();
			
		}
		
		@Autowired
		  public void configure(AuthenticationManagerBuilder auth) throws Exception {
		    auth
		      .ldapAuthentication()
		        .userDnPatterns("uid={0},ou=people")
		        .groupSearchBase("ou=groups")
		        .contextSource()
		          .url("ldap://localhost:8389/dc=springframework,dc=org")
		          .and()
		        .passwordCompare()
		          .passwordEncoder(new BCryptPasswordEncoder())
		          .passwordAttribute("userPassword");
		  }
	}
	
	@Configuration
	@Order(2)
	public static class apiauth{
		@Bean
		public SecurityFilterChain apisecurityfilterchain(HttpSecurity http) throws Exception {
			
			http.securityMatcher("/api/**").csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests((authorize) -> authorize.requestMatchers("/api/**").authenticated()).httpBasic(Customizer.withDefaults()).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).addFilterBefore(new AuthFilter(), UsernamePasswordAuthenticationFilter.class);
			
			
			return http.build();
			
		}
		
		
	}
	
	

}


	
