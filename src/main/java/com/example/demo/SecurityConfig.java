package com.example.demo;

import org.springframework.http.HttpMethod;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.example.demo.athenz.AthenzTokenFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	final PermissionEvaluator permissionEvaluator;

	public SecurityConfig(PermissionEvaluator permissionEvaluator) {
		this.permissionEvaluator = permissionEvaluator;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
		expressionHandler.setPermissionEvaluator(permissionEvaluator);

		http.authorizeRequests() //
				.expressionHandler(expressionHandler) //
				// @PreAuthorizedの代わりに以下でもOK
//				.mvcMatchers(HttpMethod.GET, "/movie/**").access("hasPermission('rec.movie', 'read')") //
//				.mvcMatchers(HttpMethod.GET, "/tvshow/**").access("hasPermission('rec.tvshow', 'read')") //
				.anyRequest().authenticated() //
				.and() //
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS) //
				.and() //
				.addFilterBefore(new AthenzTokenFilter(),
						BasicAuthenticationFilter.class);
	}
}
