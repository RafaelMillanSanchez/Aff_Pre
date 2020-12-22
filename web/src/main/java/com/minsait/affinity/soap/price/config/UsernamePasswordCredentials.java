package com.minsait.affinity.soap.price.config;
import java.security.Principal;

import org.apache.http.auth.BasicUserPrincipal;
import org.apache.http.auth.Credentials;

public class UsernamePasswordCredentials implements Credentials {
	
	
	private BasicUserPrincipal username;
	private String password;
	
	
	
	public UsernamePasswordCredentials (String username, String password) {
		this.username = new BasicUserPrincipal(username);
		this.password = password;
	}
	

	@Override
	public Principal getUserPrincipal() {
		return username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = new BasicUserPrincipal(username);
	}

}
