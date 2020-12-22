package com.minsait.affinity.security.jwt.service;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;

import com.minsait.affinity.security.jwt.model.AuthenticatedUser;
import com.minsait.affinity.security.jwt.util.JwtTokenGenerator;

public abstract class JwtSecurityService {

	@Autowired
	protected JwtTokenGenerator jwtTokenGenerator;

	protected static final String UTF_8 = "UTF-8";

	public abstract AuthenticatedUser authenticateUser(String user, String password, String organization);

	protected String decodeBase64(String data) throws UnsupportedEncodingException {

		String result = null;
		byte[] dataDecode = null;
		if (data != null)
			dataDecode = Base64.getDecoder().decode(data.getBytes(UTF_8));
		if (dataDecode != null)
			result = new String(dataDecode, UTF_8);
		return result;
	}

	protected byte[] getUserPassEncode(String user, String password) throws UnsupportedEncodingException {

		byte[] userDecode = null;
		byte[] passDecode = null;

		// Descifrar usuario y password
		if (user != null)
			userDecode = Base64.getDecoder().decode(user.getBytes(UTF_8));
		if (password != null)
			passDecode = Base64.getDecoder().decode(password.getBytes(UTF_8));
		StringBuilder userPass = new StringBuilder(new String(userDecode, UTF_8)).append(":")
				.append(new String(passDecode, UTF_8));
		return Base64.getEncoder().encode(userPass.toString().getBytes(UTF_8));
	}

}
