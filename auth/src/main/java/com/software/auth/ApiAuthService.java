package com.software.auth;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;

import jakarta.servlet.http.HttpServletRequest;

public class ApiAuthService {
	
	static String auth_header_field = "api_token";
	static String token = "test";
	
	@SuppressWarnings("null")
	public static Authentication getAuthentication(HttpServletRequest request) {
		
		String api_key = request.getHeader(auth_header_field);
		if(api_key == null || !api_key.equals("test")) {
			throw new BadCredentialsException("invalid api key");
		}
		
		return new ApiKeyAuthObj(api_key, AuthorityUtils.NO_AUTHORITIES);
		
	}

}
