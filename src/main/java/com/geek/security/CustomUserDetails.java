package com.geek.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.geek.model.Register;

public class CustomUserDetails implements UserDetails {
	
	
	
	private Register register;
	
	

	public CustomUserDetails(Register register) {
		this.register = register;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of(new SimpleGrantedAuthority(register.getUserRole()));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return register.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		
				
		return register.getEmail();
	}
	


	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
