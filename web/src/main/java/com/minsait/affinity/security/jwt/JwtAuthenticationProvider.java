package com.minsait.affinity.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.minsait.affinity.security.jwt.exception.JwtTokenExpirationException;
import com.minsait.affinity.security.jwt.exception.JwtTokenMalformedException;
import com.minsait.affinity.security.jwt.model.AuthenticatedUser;
import com.minsait.affinity.security.jwt.model.JwtAuthenticationToken;
import com.minsait.affinity.security.jwt.model.JwtUserDto;
import com.minsait.affinity.security.jwt.util.JwtTokenValidator;

/**
 * Used for checking the token from the request and supply the UserDetails 
 * if the token is valid.
 */
@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private JwtTokenValidator jwtTokenValidator;

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }

    @Override
    protected void additionalAuthenticationChecks(
    		UserDetails userDetails, 
    		UsernamePasswordAuthenticationToken authentication) 
    	throws AuthenticationException {
    }

    @Override
    protected UserDetails retrieveUser(
    		String username, 
    		UsernamePasswordAuthenticationToken authentication) 
    	throws AuthenticationException {
    	
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
        String token = jwtAuthenticationToken.getToken();

        JwtUserDto parsedUser = jwtTokenValidator.parseToken(token);

        if (parsedUser == null) {
            throw new JwtTokenMalformedException("JWT token is not valid");
        }
        
        // Check: Expiration Time
        if (jwtTokenValidator.checkExpirationTimeEnd(parsedUser)) {
        	throw new JwtTokenExpirationException("JWT token has expired");
        }

//        List<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>();
//        if (StringUtils.hasLength(parsedUser.getRole())) {
//        	authorityList = 
//        			AuthorityUtils.commaSeparatedStringToAuthorityList(parsedUser.getRole());        	
//        }

        return new AuthenticatedUser(parsedUser, null);
    }
}
