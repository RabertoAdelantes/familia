package com.ra.security.config;

import javax.servlet.ServletContext;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityInit extends AbstractSecurityWebApplicationInitializer {
	@Override
	protected void afterSpringSecurityFilterChain(ServletContext servletContext) {
		super.afterSpringSecurityFilterChain(servletContext);
		System.out.println(">>>>>>>>>>>>>>>>>> afterSpringSecurityFilterChain");
	}
	
	@Override
	protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
		super.beforeSpringSecurityFilterChain(servletContext);
		System.out.println(">>>>>>>>>>>>>>>>>> beforeSpringSecurityFilterChain");

	}
	
}
