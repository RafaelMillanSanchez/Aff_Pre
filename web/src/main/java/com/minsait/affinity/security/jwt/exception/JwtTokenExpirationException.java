package com.minsait.affinity.security.jwt.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Thrown when token has expired 
 */
public class JwtTokenExpirationException extends AuthenticationException {

	private static final long serialVersionUID = -4650407255971569600L;

	public JwtTokenExpirationException(String msg) {
        super(msg);
    }

}
