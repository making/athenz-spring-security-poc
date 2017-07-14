package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

import com.example.demo.athenz.AthenzPermissionEvaluator;
import com.example.demo.athenz.Authorizer;
import com.example.demo.athenz.DefaultTokenAuthorizer;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class GlobalMethodSecurityConfig extends GlobalMethodSecurityConfiguration {

	@Override
	protected MethodSecurityExpressionHandler createExpressionHandler() {
		DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
		expressionHandler.setPermissionEvaluator(permissionEvaluator());
		return expressionHandler;
	}

	@Bean
	public Authorizer authorizer() {
		return new DefaultTokenAuthorizer();
	}

	@Bean
	public PermissionEvaluator permissionEvaluator() {
		return new AthenzPermissionEvaluator(authorizer());
	}
}
