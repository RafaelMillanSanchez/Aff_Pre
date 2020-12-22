package com.minsait.affinity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.minsait.affinity.security.jwt.JwtAuthenticationEntryPoint;
import com.minsait.affinity.security.jwt.JwtRequestFilter;
import com.minsait.affinity.service.JwtUserDetailsService;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled  = true)
@ComponentScan("com.minsait.affinity.security")
@Configuration
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Value("${jwt.header}")
    private String tokenHeader;

	@Value("${jwt.header.bearer}")
    private String tokenHeaderBearer;
	
	@Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Autowired
    private JwtUserDetailsService jwtUserDetailsService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		 auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
    public JwtAuthenticationEntryPoint jwtAuthenticationEntryPointBean() throws Exception{
        return new JwtAuthenticationEntryPoint();
    }
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	/*@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("**");
	}

	public Filter getBasicAuthFilter() {
		BasicAuthenticationFilter filter = new BasicAuthenticationFilter();
		filter.setUsername("affinity");
		filter.setPassword("affinity123");
		return filter;
	}*/
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests().antMatchers("/authenticate").permitAll()
		.anyRequest().authenticated().and().
/*
		http.csrf().disable()
		 .authorizeRequests().antMatchers("/**").permitAll().and().
*/
		exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		 
		 http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

	}

	/*@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticator());
		super.configure(auth);
	}

	@Bean
	public AuthenticationProvider authenticator() {
		return new JwtAuthenticationProvider();
	}*/

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	/*public Filter authenticationTokenFilter() throws Exception {
		JwtAuthenticationTokenFilter filter = new JwtAuthenticationTokenFilter();
		filter.setTokenHeader(this.tokenHeader);
		filter.setTokenHeaderBearer(this.tokenHeaderBearer);
		filter.setAuthenticationSuccessHandler(new JwtAuthenticationSuccessHandler());
		filter.setAuthenticationManager(super.authenticationManagerBean());
		return filter;
	}*/

}