package com.software.auth;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class ApiKeyAuthObj extends AbstractAuthenticationToken{
	
	private static final long serialVersionUID = 1L;
	final String api_key;

	public ApiKeyAuthObj(String api_key,Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.api_key = api_key;
		setAuthenticated(true);
		
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return api_key;
	}

}
