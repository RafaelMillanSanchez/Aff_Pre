package com.minsait.affinity.security.jwt.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *  Holds the info for a authenticated user (Principal)
 */
public class AuthenticatedUser implements UserDetails {

	private static final long serialVersionUID = 5013178279069064932L;	
    
	@JsonIgnore
    private String token;
	
	public void setToken(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	@JsonIgnore
	private JwtUserDto parsedUser;
	
	@JsonIgnore
	private List<GrantedAuthority> authorityList;
	
	public AuthenticatedUser(JwtUserDto parsedUser, List<GrantedAuthority> authorityList) {
		this.parsedUser = parsedUser;
		this.authorityList = authorityList;		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {		
		return authorityList;
	}

	@Override
	public String getPassword() {
		return "[PROTECTED]";
	}

	@Override
	public String getUsername() {
		return this.parsedUser.getId().toString();
	}
	
	public String getOrganization() {
		return this.parsedUser.getOrganization();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
