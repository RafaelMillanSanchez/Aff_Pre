package com.minsait.affinity.security.jwt.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Thrown when token cannot be parsed.
 */
public class JwtTokenMalformedException extends AuthenticationException {

	private static final long serialVersionUID = -7096368222412761947L;

	public JwtTokenMalformedException(String msg) {
        super(msg);
    }
}
