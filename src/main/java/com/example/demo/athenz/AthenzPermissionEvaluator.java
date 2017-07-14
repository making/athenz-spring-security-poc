package com.example.demo.athenz;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

public class AthenzPermissionEvaluator implements PermissionEvaluator {
	private static final Logger log = LoggerFactory
			.getLogger(AthenzPermissionEvaluator.class);
	private final Authorizer authorizer;

	public AthenzPermissionEvaluator(Authorizer authorizer) {
		this.authorizer = authorizer;
	}

	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject,
			Object permission) {
		String action = (String) permission;
		String resource = (String) targetDomainObject;
		String token = (String) authentication.getCredentials();
		log.info("check permission(action={}, resource={}, token={})", action, resource,
				token);
		return this.authorizer.access(action, resource, token);
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId,
			String targetType, Object permission) {
		log.warn(
				"Not supported:\thasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission)");
		return false;
	}
}
