package com.minsait.affinity.security.jwt.service;

import org.springframework.stereotype.Service;

import com.minsait.affinity.security.jwt.model.AuthenticatedUser;

@Service
public class UserService extends JwtSecurityService {
	
	@Override
	public AuthenticatedUser authenticateUser(String user, String password, String organization) {
		return null;
	}
	

}
