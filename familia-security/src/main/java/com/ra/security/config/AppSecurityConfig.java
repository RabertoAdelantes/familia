package com.ra.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ra.familia.services.EncryptionService;
import com.ra.security.config.filter.CustomUsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter;

	@Autowired
	private com.ra.security.services.UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private EncryptionService encryptionService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
	}

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
				.antMatchers("/protected/**").access("hasRole('ROLE_ADMIN')")
				.and().formLogin().loginPage("/login");
		http.addFilter(customUsernamePasswordAuthenticationFilter);
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	public PasswordEncoder getPasswordEncoder() {
		PasswordEncoder encoder = new PasswordEncoder() {
			@Override
			public String encode(CharSequence charSequence) {
				return encryptionService.encode(String.valueOf(charSequence));
			}

			@Override
			public boolean matches(CharSequence rawPass, String encodedPass) {
				return encode(rawPass).equals(encodedPass);
			}
		};
		return encoder;
	}

}
