package com.ra.security.config.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		super.doFilter(req, res, chain);
		super.setAuthenticationSuccessHandler(getSuccessHandler());
		super.setAuthenticationFailureHandler(getFailureHandler());
	}

	@Override
	@Autowired
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		super.setAuthenticationManager(authenticationManager);
	}

	@Override
	protected AuthenticationSuccessHandler getSuccessHandler() {
		SavedRequestAwareAuthenticationSuccessHandler successHandler = (SavedRequestAwareAuthenticationSuccessHandler) super.getSuccessHandler();
		successHandler.setDefaultTargetUrl("/search");
		return successHandler;
	}

	@Override
	protected AuthenticationFailureHandler getFailureHandler() {
		SimpleUrlAuthenticationFailureHandler simpleUrlAuthenticationFailureHandler = (SimpleUrlAuthenticationFailureHandler) super.getFailureHandler();
		simpleUrlAuthenticationFailureHandler.setDefaultFailureUrl("/logout");
		return super.getFailureHandler();
	}
}
