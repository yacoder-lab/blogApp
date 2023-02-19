package com.geek.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.geek.model.Register;
import com.geek.repo.RegisterRepository;

public class CustomUserDetailsServiceImpl implements UserDetailsService {

	
	@Autowired
	private RegisterRepository registerRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		Register findByEmail = this.registerRepository.getByEmail(username);
		
		if(findByEmail==null) {
			
			throw new UsernameNotFoundException("Could Not Found Any UserName or Email");
		}
		CustomUserDetails customUserDetails = new CustomUserDetails(findByEmail);
		return customUserDetails;
	}

}
