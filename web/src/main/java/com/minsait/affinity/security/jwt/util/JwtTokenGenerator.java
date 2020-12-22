package com.minsait.affinity.security.jwt.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.minsait.affinity.security.jwt.model.JwtUserDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Convenience class to generate a token for check your requests. Make sure the
 * used secret here matches the on in your application.xml
 */
@Component
public class JwtTokenGenerator {

	@Value("${jwt.secret.key:changeme}")
	private String secretKey;

	@Value("${jwt.expiration.hours:1}")
	private Integer expirationHours;

	/**
	 * Generates a JWT token containing username as subject, and userId and role as
	 * additional claims. These properties are taken from the specified User object.
	 * Tokens validity is infinite.
	 *
	 * @param u
	 *            the user for which the token will be generated
	 * @return the JWT token
	 */
	public String generateToken(JwtUserDto u) {
		Claims claims = Jwts.claims().setSubject(u.getId().toString());

		Calendar calendar = GregorianCalendar.getInstance();
		calendar.add(Calendar.HOUR_OF_DAY, expirationHours);
		claims.put("exp", calendar.getTimeInMillis());
		claims.put("user", u.getId().toString());
		claims.put("org", u.getOrganization());
		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secretKey).compact();
	}

}
