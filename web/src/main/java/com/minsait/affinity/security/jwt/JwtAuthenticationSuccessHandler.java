package com.minsait.affinity.security.jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * Defines where to go after successful login. 
 * In this implementation just make sure nothing is done (REST API constains no pages)
 */
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(
			HttpServletRequest request,
			HttpServletResponse response, 
			Authentication authentication) 
		throws IOException, ServletException {
		// We do not need to do anything extra on REST authentication success, 
		// because there is no page to redirect to
	}

}
