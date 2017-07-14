package com.example.demo.athenz;

public class DefaultTokenAuthorizer implements Authorizer {
	@Override
	public boolean access(String action, String resource, String token) {
		return token.equalsIgnoreCase("valid");
	}
}