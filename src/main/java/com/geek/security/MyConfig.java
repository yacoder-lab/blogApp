package com.geek.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@Configuration
@EnableWebSecurity
public class MyConfig extends WebSecurityConfigurerAdapter {


	@Bean
	public UserDetailsService getDetailsService() {
		
		return new CustomUserDetailsServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
	
		daoAuthenticationProvider.setUserDetailsService(getDetailsService());
		
		daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
		
		return daoAuthenticationProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	
		
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests().antMatchers("/post/**").hasRole("NORMAL")
		.antMatchers("/**").permitAll().and().formLogin().loginPage("/login").defaultSuccessUrl("/post/cms",true).and().csrf().disable().cors().disable();
	}


	
	
	
	


}
