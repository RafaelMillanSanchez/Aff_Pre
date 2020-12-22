package com.minsait.affinity.security.jwt.model;

import java.io.Serializable;


/**
 * Simple placeholder for info extracted from the JWT.
 */
public class JwtUserDto implements Serializable {

	private static final long serialVersionUID = 3215552197744898481L;
	
	private Long expirationTime;

	private Long id;
	
	private String organization;
	
	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public JwtUserDto() {
	}

	public Long getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Long expirationTime) {
		this.expirationTime = expirationTime;
	}
    
}
