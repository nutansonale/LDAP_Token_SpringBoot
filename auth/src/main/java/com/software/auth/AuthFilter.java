package com.software.auth;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthFilter extends GenericFilterBean{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		try {
			
			Authentication authentication = ApiAuthService.getAuthentication((HttpServletRequest)request);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
		}
		catch(Exception exp){
			System.out.println("exception happned");
			HttpServletResponse httpresponse = (HttpServletResponse) response;
			httpresponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			httpresponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
			PrintWriter writer = httpresponse.getWriter();
			writer.print(exp.getMessage());
			writer.flush();
			writer.close();
			
		}
		
		chain.doFilter(request, response);
		
	}

}
