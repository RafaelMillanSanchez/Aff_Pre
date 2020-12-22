package com.minsait.affinity.security.jwt.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Thrown when token cannot be found in the request header
 */
public class JwtTokenMissingException extends AuthenticationException {

	private static final long serialVersionUID = 752432830823974591L;

	public JwtTokenMissingException(String msg) {
        super(msg);
    }

}
