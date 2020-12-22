package com.minsait.affinity.security.jwt.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public abstract class AbstractJwtController {
	
	
	@Value("${jwt.header}")
	private String tokenHeader;

	@Value("${jwt.header.bearer}")
	private String tokenHeaderBearer;
	
	protected HttpHeaders getJwtHeaders(String token){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add(tokenHeader, tokenHeaderBearer + " " + token);
		return headers;
	}

}
