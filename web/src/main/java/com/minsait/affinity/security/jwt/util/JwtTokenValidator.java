package com.minsait.affinity.security.jwt.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.minsait.affinity.security.jwt.model.JwtUserDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

/**
 * Class validates a given token by using the secret configured in the application.
 */
@Component
public class JwtTokenValidator {

	@Value("${jwt.secret.key}")
    private String secretKey;

    /**
     * Tries to parse specified String as a JWT token. If successful, returns User object with username, 
     * id and role prefilled (extracted from token). If unsuccessful (token is invalid or not containing 
     * all required user properties), simply returns null.
     *
     * @param token the JWT token to parse
     * @return the User object extracted from specified token or null if a token is invalid.
     */
    public JwtUserDto parseToken(String token) {
        JwtUserDto u = null;

        try {
            Claims body = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();

            u = new JwtUserDto();
            u.setExpirationTime((Long) body.get("exp"));
            u.setId(Long.parseLong((String)body.get("user")));
            u.setOrganization((String) body.get("org"));
        } catch (JwtException e) {
            // Simply null will be returned for the userDto
        }
        return u;
    }
    
    /**
     * checks whether it has completed the expiration time 
     * @param user user data
     * @return result of check the expiration time
     */
    public Boolean checkExpirationTimeEnd(JwtUserDto user) {
    	Boolean result = Boolean.FALSE;
    	Calendar expirationDate = new GregorianCalendar();
    	
    	if ((user == null) || 
    		(user.getExpirationTime() == null) || 
    		(user.getExpirationTime().compareTo(expirationDate.getTimeInMillis()) < 0)) {
    		result = Boolean.TRUE;
    	}
    		
    	return result;
    }
}
