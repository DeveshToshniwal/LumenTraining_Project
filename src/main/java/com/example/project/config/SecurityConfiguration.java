package com.example.project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
	protected void configure(HttpSecurity http) throws Exception
	{
		//to avoid the login page for now
		http.httpBasic().disable();

		//added currently as CSRF is not enabled.
		http.csrf().disable();

		//adding CSP headers for XSS Prevention in case of UI calls
		http.headers().xssProtection().block(true).and()
				.contentSecurityPolicy(
						"default-src 'self'; "
								+"script-src 'self'"
								+"object-src 'self'; "
								+"img-src 'self'; media-src 'self'; "
								+"frame-src 'self'; font-src 'self'; "
								+"connect-src 'self'");
	}

}
