package com.software.auth;

import javax.naming.directory.DirContext;

import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.ldap.authentication.AbstractLdapAuthenticator;

public class CustomAuthProvider extends AbstractLdapAuthenticator {
	ContextSource ctxsource;
	
	public CustomAuthProvider(ContextSource contextSource) {
		super(contextSource);
		this.ctxsource = contextSource;
		// TODO Auto-generated constructor stub
	}

	@Override
	public DirContextOperations authenticate(Authentication authentication) throws AuthenticationException {
		DirContext ctx;
		
        String username = authentication.getName();
        String password = (String)authentication.getCredentials();
        
        ctx = ctxsource.getContext(username, password);

        
		return null;
	}

	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return false;
	}

}
