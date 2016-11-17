package com.novellius.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomAuthenticationProvider implements AuthenticationProvider{

	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String principal = authentication.getName();
		String credencials = (String)  authentication.getCredentials();
		
		User user = (User) customUserDetailsService.loadUserByUsername(principal);
		
		if(user != null){
			if(passwordEncoder.matches(credencials, user.getPassword())){
				System.out.println("login correcto");
				return new UsernamePasswordAuthenticationToken(credencials, user.getPassword(), user.getAuthorities());
			}else{
				System.out.println("Erro de Login" + principal);
				throw new BadCredentialsException("Erro de login");
			}
		}else{
			System.out.println("Erro de Login" + principal);
			throw new BadCredentialsException("Erro de login");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}

	
	
	
}
