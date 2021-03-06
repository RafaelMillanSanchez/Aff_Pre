package com.minsait.affinity.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.minsait.affinity.security.jwt.exception.JwtTokenMissingException;
import com.minsait.affinity.security.jwt.model.JwtAuthenticationToken;

/**
 * Filter that orchestrates authentication by using supplied JWT token.
 */
public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

	private String tokenHeader;

	private String tokenHeaderBearer;

	public void setTokenHeader(String tokenHeader) {
		this.tokenHeader = tokenHeader;
	}

	public void setTokenHeaderBearer(String tokenHeaderBearer) {
		this.tokenHeaderBearer = tokenHeaderBearer;
	}

	public JwtAuthenticationTokenFilter() {
		super("/**");
	}

	@Override
	protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
		return true;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {

		String header = request.getHeader(this.tokenHeader);
		if (header == null || !header.startsWith(tokenHeaderBearer)) {
			throw new JwtTokenMissingException("No JWT token found in request headers");
		}

		String authToken = header.substring(7);
		JwtAuthenticationToken authRequest = new JwtAuthenticationToken(authToken);
		return getAuthenticationManager().authenticate(authRequest);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		super.successfulAuthentication(request, response, chain, authResult);

		// As this authentication is in HTTP header, after success we need to continue
		// the request normally
		// and return the response as if the resource was not secured at all
		chain.doFilter(request, response);
	}

}
