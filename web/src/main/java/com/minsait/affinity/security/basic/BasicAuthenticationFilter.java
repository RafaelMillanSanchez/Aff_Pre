package com.minsait.affinity.security.basic;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasicAuthenticationFilter implements Filter {

	/** Logger */
	private static final Logger LOG = LoggerFactory.getLogger(BasicAuthenticationFilter.class);

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String username = null;

	private String password = null;

	private String realm = "Protected";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		username = filterConfig.getInitParameter("username");
		password = filterConfig.getInitParameter("password");
		String paramRealm = filterConfig.getInitParameter("realm");
		if (StringUtils.isNotBlank(paramRealm)) {
			realm = paramRealm;
		}
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		String authHeader = request.getHeader("Authorization");
		if (authHeader != null) {
			StringTokenizer st = new StringTokenizer(authHeader);
			if (st.hasMoreTokens()) {
				String basic = st.nextToken();

				if (basic.equalsIgnoreCase("Basic")) {
					try {
						String credentials = new String(Base64.getDecoder().decode(st.nextToken()), "UTF-8");
						LOG.debug("Credentials: " + credentials);
						int p = credentials.indexOf(":");
						if (p != -1) {
							String _username = credentials.substring(0, p).trim();
							String _password = credentials.substring(p + 1).trim();

							if (!username.equals(_username) || !password.equals(_password)) {
								unauthorized(response, "Bad credentials");
							}

							filterChain.doFilter(servletRequest, servletResponse);
						} else {
							unauthorized(response, "Invalid authentication token");
						}
					} catch (UnsupportedEncodingException e) {
						throw new Error("Couldn't retrieve authentication", e);
					}
				}
			}
		} else {
			unauthorized(response);
		}
	}

	@Override
	public void destroy() {
	}

	private void unauthorized(HttpServletResponse response, String message) throws IOException {
		response.setHeader("WWW-Authenticate", "Basic realm=\"" + realm + "\"");
		response.sendError(401, message);
	}

	private void unauthorized(HttpServletResponse response) throws IOException {
		unauthorized(response, "Unauthorized");
	}

}